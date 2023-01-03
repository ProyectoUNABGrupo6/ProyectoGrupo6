package grupo6.proyectogrupo6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Categoria;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class AgregarCategoria extends AppCompatActivity {

    private DBHelper dbHelper;
    private DBFirebase dbFirebase;
    private ImageButton imgAddCat;
    private TextView usuCat, idCat;
    private EditText insAddCat;
    public FloatingActionButton botonAgregar, botonAtras, botonActualizarCat;
    private ProductosServices productosServices;
    public String urlImagenCategoria;
    private StorageReference storageReference;
    ActivityResultLauncher<String> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_categoria);

        imgAddCat = findViewById(R.id.imgAddCat);
        insAddCat = findViewById(R.id.insCategoria);
        idCat = findViewById(R.id.txtIDAddCat);
        botonActualizarCat = findViewById(R.id.btnActualizarCat);
        botonAgregar = findViewById(R.id.btnAddCat);

        usuCat = findViewById(R.id.txtUsuCat);
        botonAtras = findViewById(R.id.btnAtrasCat);
        productosServices = new ProductosServices();
        storageReference = FirebaseStorage.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String usuario = bundle.getString("usuario");
            idCat.setText(bundle.getString("id"));
            insAddCat.setText(bundle.getString("categoria"));
            usuCat.setText(usuario);
        }

        try {
            dbFirebase = new DBFirebase();
            dbHelper = new DBHelper(this);
        } catch (Exception e) {
            Log.e("DB", e.toString());
        }

        content = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                result -> {
                    StorageReference filePath = storageReference.child("imagenes").child(result.getLastPathSegment());
                    filePath.putFile(result)
                            .addOnSuccessListener(taskSnapshot -> {
                                Toast.makeText(getApplicationContext(), "Imagen Cargada", Toast.LENGTH_SHORT).show();
                                filePath.getDownloadUrl().addOnSuccessListener(uri1 -> {
                                    urlImagenCategoria = uri1.toString();
                                    productosServices.insertarImagen(urlImagenCategoria, imgAddCat, AgregarCategoria.this);
                                });
                            });
                }
        );

        imgAddCat.setOnClickListener(View -> content.launch("image/*"));

        botonAgregar.setOnClickListener(View -> {

            if (!(insAddCat.getText().toString()).isEmpty()) {
                try {
                    Categoria categoria = new Categoria(
                            insAddCat.getText().toString(),
                            urlImagenCategoria
                    );
                    dbHelper.insertarCategorias(categoria);
                    dbFirebase.insertarCategorias(categoria);
                    volverAtras(View);
                } catch (Exception e) {
                    Log.e("DBCat Insert", e.toString());
                }
            }

        });

        botonActualizarCat.setOnClickListener(view ->{
            dbFirebase.actualizarCategoria(
                    idCat.getText().toString(),
                    insAddCat.getText().toString(),
                    urlImagenCategoria
            );
            dbHelper.actualizarCategorias(
                    idCat.getText().toString(),
                    insAddCat.getText().toString(),
                    urlImagenCategoria
            );
        } );


        botonAtras.setOnClickListener(this::volverAtras);
    }

    public void volverAtras(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("usuario", usuCat.getText().toString());
        startActivity(intent);
    }
}