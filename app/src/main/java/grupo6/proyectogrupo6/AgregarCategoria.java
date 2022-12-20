package grupo6.proyectogrupo6;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Categoria;

public class AgregarCategoria extends AppCompatActivity {

    private DBHelper dbHelper;
    private DBFirebase dbFirebase;
    ActivityResultLauncher<String> content;

    private ImageButton imgAddCat, botonAtras;
    private ImageView imgTitulo;
    private TextView usuCat;
    private EditText insAddCat;
    private FloatingActionButton botonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_categoria);

        imgAddCat = findViewById(R.id.imgAddCat);
        insAddCat = findViewById(R.id.insCategoria);
        botonAgregar = findViewById(R.id.btnAddCat);
        imgTitulo = findViewById(R.id.imgTituloCat);
        usuCat = findViewById(R.id.txtUsuCat);
        botonAtras = findViewById(R.id.imgAtrasCat);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String usuario = bundle.getString("usuario");
            int imgTit = bundle.getInt("imageTitulo");
            int imgAtras = bundle.getInt("imageAtras");
            usuCat.setText(usuario);
            botonAtras.setImageResource(imgAtras);
            imgTitulo.setImageResource(imgTit);
        }


        try {
            dbFirebase = new DBFirebase();
            dbHelper = new DBHelper(this);
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        content = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                result -> imgAddCat.setImageURI(result)
        );

        imgAddCat.setOnClickListener(View ->
                content.launch("image*/"));

        botonAgregar.setOnClickListener(View ->{

            if(!(insAddCat.getText().toString()).isEmpty()){
                try {
                    Categoria categoria = new Categoria(
                            insAddCat.getText().toString(),
                            ""
                    );
                    dbHelper.insertarCategorias(categoria);
                    dbFirebase.insertarCategorias(categoria);
                    volverAtras(View);
                }catch (Exception e){
                    Log.e("DBCat Insert", e.toString());
                }
            }

        });


        botonAtras.setOnClickListener(this::volverAtras);
    }

    public void volverAtras(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("usuario", usuCat.getText().toString());

        intent.putExtra("imageTitulo", R.drawable.ferresix);
        intent.putExtra("imageCarrito", R.drawable.carrito);
        startActivity(intent);
    }
}