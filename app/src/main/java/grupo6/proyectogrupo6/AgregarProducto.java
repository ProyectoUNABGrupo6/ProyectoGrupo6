package grupo6.proyectogrupo6;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class AgregarProducto extends AppCompatActivity {

    private ProductosServices productosServices;
    private DBFirebase dbFirebase;
    private DBHelper dbHelper;
    public FloatingActionButton botonAgregarPro, btnActualizar, botonAtrasForm;
    private EditText productoAdd, descripcionAdd, precioAdd, categoriaAdd;
    private ImageButton imgAdd;
    private TextView idAct, usuA;
    private StorageReference storageReference;
    public String urlImagen;



    ActivityResultLauncher<String> content;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        botonAgregarPro = findViewById(R.id.botonAgregar);
        productoAdd = findViewById(R.id.insNombre);
        descripcionAdd = findViewById(R.id.insDescripcion);
        precioAdd = findViewById(R.id.insPrecio);
        categoriaAdd = findViewById(R.id.insCatProd);
        imgAdd = findViewById(R.id.imgAgregar);
        btnActualizar = findViewById(R.id.btnActualizar);
        idAct = findViewById(R.id.txtIDAct);
        storageReference = FirebaseStorage.getInstance().getReference();
        botonAtrasForm = findViewById(R.id.btnAtrasAddP);
        usuA = findViewById(R.id.txtUsuProd);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String usuario = bundle.getString("usuario");
            usuA.setText(usuario);
            String cat = bundle.getString("categoria");
            categoriaAdd.setText(cat);

            String ida = bundle.getString("id");
            if (ida != null) {
                String id = bundle.getString("id");
                String tituloInf = bundle.getString("titulo");
                String DescripInf = bundle.getString("descripcion");
                String categoriaInf = bundle.getString("categoria");
                String imagen = bundle.getString("imagen");
                String precInf = bundle.getString("precio");
                precInf = precInf.replaceAll("[$]", "");
                productoAdd.setText(tituloInf);
                descripcionAdd.setText(DescripInf);
                categoriaAdd.setText(categoriaInf);
                productosServices = new ProductosServices();
                productosServices.insertarImagen(imagen, imgAdd, AgregarProducto.this);
                precioAdd.setText(precInf);
                idAct.setText(id);
            }
        }

        try {

            productosServices = new ProductosServices();
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
                                    urlImagen = uri1.toString();
                                    productosServices.insertarImagen(urlImagen, imgAdd, AgregarProducto.this);
                                });
                            });
                }
        );

        imgAdd.setOnClickListener(View -> content.launch("image/*"));

        botonAgregarPro.setOnClickListener(View -> {


            if (!(productoAdd.getText().toString()).isEmpty() && !(descripcionAdd.getText().toString()).isEmpty() && !(precioAdd.getText().toString()).isEmpty()) {
                try {
                    Producto producto = new Producto(
                            productoAdd.getText().toString(),
                            descripcionAdd.getText().toString(),
                            categoriaAdd.getText().toString(),
                            Integer.parseInt(precioAdd.getText().toString()),
                            urlImagen

                    );
                    dbHelper.insertarDatos(producto);
                    dbFirebase.insertarDatos(producto);
                    volverAtras(View);
                } catch (Exception e) {
                    Log.e("DB Insert", e.toString());
                }
            } else {
                Toast.makeText(this, "Los campos no deben estar vacios", Toast.LENGTH_LONG).show();
            }
        });

        btnActualizar.setOnClickListener(View -> {

            dbFirebase.actualizarDatos(
                    idAct.getText().toString(),
                    productoAdd.getText().toString(),
                    descripcionAdd.getText().toString(),
                    Integer.parseInt(precioAdd.getText().toString()),
                    urlImagen,
                    categoriaAdd.getText().toString()

            );
            dbHelper.actualizarDatos(
                    idAct.getText().toString(),
                    productoAdd.getText().toString(),
                    descripcionAdd.getText().toString(),
                    Integer.parseInt(precioAdd.getText().toString()),
                    urlImagen,
                    categoriaAdd.getText().toString()

            );
            volverAtras(View);
        });


        botonAtrasForm.setOnClickListener(this::volverAtras);


    }


    public void volverAtras(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("usuario", usuA.getText().toString());
        intent.putExtra("categoria", categoriaAdd.getText().toString());
        startActivity(intent);
    }


}