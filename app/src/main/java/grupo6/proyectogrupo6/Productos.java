package grupo6.proyectogrupo6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import grupo6.proyectogrupo6.Adapters.ProductoAdapters;
import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class Productos extends AppCompatActivity {

    public DBHelper dbHelper;
    public DBFirebase dbFirebase;
    public ProductosServices productosServices;
    public ImageButton botonAtras;
    public ImageView imgTitulo;
    public ImageView imgCarrito;


    public ListView listViewProductos;
    public ArrayList<Producto> arrayList;
    public ProductoAdapters productoAdapters;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        arrayList = new ArrayList<>();


        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();

            productosServices = new ProductosServices();
            Cursor cursor = dbHelper.consultarDatos();
            arrayList = productosServices.cursorToArray(cursor);


        } catch (Exception e) {
            Log.e("Database", e.toString());
        }

        botonAtras = findViewById(R.id.imgAtras);
        imgTitulo = findViewById(R.id.imgTituloProductoTemplate);
        imgCarrito = findViewById(R.id.imgCarritoProductos);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgTit = bundle.getInt("imageTitulo");
            int imgCar = bundle.getInt("imageCarrito");
            imgTitulo.setImageResource(imgTit);
            imgCarrito.setImageResource(imgCar);
        }

        botonAtras.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });


        productoAdapters = new ProductoAdapters(this, arrayList);

        listViewProductos = findViewById(R.id.listViewProductos);
        listViewProductos.setAdapter(productoAdapters);
        dbFirebase.buscarDatos(productoAdapters, arrayList);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.actionAdd) {
            Intent intent = new Intent(getApplicationContext(), AgregarProducto.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}