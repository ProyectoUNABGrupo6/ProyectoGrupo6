package grupo6.proyectogrupo6;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class AgregarProducto extends AppCompatActivity {

    private ProductosServices productosServices;
    private DBHelper dbHelper;
    public Button botonAgregarPro;
    private EditText productoAdd, descripcionAdd, precioAdd;
    private ImageButton imgAdd;
    ActivityResultLauncher<String> content;

    public ImageButton botonAtrasForm;
    public ImageView imgTituloForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        botonAgregarPro = findViewById(R.id.botonAgregar);
        productoAdd = findViewById(R.id.insNombre);
        descripcionAdd = findViewById(R.id.insDescripcion);
        precioAdd = findViewById(R.id.insPrecio);
        imgAdd = findViewById(R.id.imgAgregar);

        botonAtrasForm = findViewById(R.id.imgAtrasForm);
        imgTituloForm = findViewById(R.id.imgTituloForm);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgTit = bundle.getInt("imageTitulo");
            int imgAtras = bundle.getInt("imageAtras");
            botonAtrasForm.setImageResource(imgAtras);
            imgTituloForm.setImageResource(imgTit);
        }


        try {

            productosServices = new ProductosServices();
            dbHelper = new DBHelper(this);

        } catch (Exception e) {
            Log.e("DB", e.toString());
        }

        content = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                result -> imgAdd.setImageURI(result)
        );

        imgAdd.setOnClickListener(View -> content.launch("image/*"));


        botonAgregarPro.setOnClickListener(View -> {

            if (!(productoAdd.getText().toString()).isEmpty() && !(descripcionAdd.getText().toString()).isEmpty() && !(precioAdd.getText().toString()).isEmpty()) {

                dbHelper.insetarDatos(
                        productoAdd.getText().toString(),
                        descripcionAdd.getText().toString(),
                        Integer.parseInt(precioAdd.getText().toString()),
                        productosServices.imageButtonToByte(imgAdd)

                );

                volverAtras(View);
            } else {
                Toast.makeText(this, "Los campos no deben estar vacios", Toast.LENGTH_LONG).show();
            }
        });


        botonAtrasForm.setOnClickListener(this::volverAtras);


    }


    public void volverAtras(View view) {
        Intent intent = new Intent(getApplicationContext(), Productos.class);
        intent.putExtra("imageAtras", R.mipmap.atras);
        intent.putExtra("imageTitulo", R.drawable.ferresix);
        intent.putExtra("imageCarrito", R.drawable.carrito);
        startActivity(intent);
    }


}