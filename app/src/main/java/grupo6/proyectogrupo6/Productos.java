package grupo6.proyectogrupo6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import grupo6.proyectogrupo6.Adapters.ProductoAdapters;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class Productos extends AppCompatActivity {

    private DBHelper dbHelper;
    private ProductosServices productosServices;
    public ImageButton botonAtras;
    public ImageView imgTitulo;
    public ImageView imgCarrito;

    public ListView listViewProductos;
    public ArrayList<Producto> arrayList;
    public ProductoAdapters productoAdapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);


        try {
            dbHelper = new DBHelper(this);
            /*
            byte[] img = "".getBytes();
            dbHelper.insetarDatos("Producto1", "Descripcion1", 10000, img);
            dbHelper.insetarDatos("Producto2", "Descripcion2", 70000, img);
            dbHelper.insetarDatos("Producto3", "Descripcion3", 20000, img);
            dbHelper.insetarDatos("Producto4", "Descripcion4", 90000, img);
            dbHelper.insetarDatos("Producto5", "Descripcion5", 80000, img);
            dbHelper.insetarDatos("Producto6", "Descripcion6", 60000, img);
            dbHelper.insetarDatos("Producto7", "Descripcion7", 40000, img);
            dbHelper.insetarDatos("Producto8", "Descripcion8", 50000, img);
            dbHelper.insetarDatos("Producto9", "Descripcion9", 30000, img);*/


            productosServices = new ProductosServices();
            Cursor cursor = dbHelper.consultarDatos();
            arrayList = productosServices.cursorToArray(cursor);
            Toast.makeText(this, "Insert OK", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(this, "Error Lectura de DB", Toast.LENGTH_LONG).show();
        }

        botonAtras = findViewById(R.id.imgAtras);
        imgTitulo = findViewById(R.id.imgTituloProductoTemplate);
        imgCarrito = findViewById(R.id.imgCarritoProductos);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgTit = bundle.getInt("imageTitulo");
            int imgCar = bundle.getInt("imageCarrito");
            imgTitulo.setImageResource(imgTit);
            imgCarrito.setImageResource(imgCar  );
        }

        botonAtras.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });


        productoAdapters = new ProductoAdapters(this, arrayList);

        listViewProductos = findViewById(R.id.listViewProductos);
        listViewProductos.setAdapter(productoAdapters);


    }


}