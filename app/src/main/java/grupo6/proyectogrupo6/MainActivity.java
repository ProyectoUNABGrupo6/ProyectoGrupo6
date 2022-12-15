package grupo6.proyectogrupo6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class MainActivity extends AppCompatActivity {

    public DBHelper dbHelper;
    public DBFirebase dbFirebase;
    public ProductosServices productosServices;
    public ArrayList<Producto> arrayList;


    public ImageButton imgMaterial;
    public ImageButton imgHerramientas;
    public ImageButton imgElectricos;
    public TextView txtMaterial;
    public TextView txtHerramienta;
    public TextView txtElectrico;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();


        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();

            productosServices = new ProductosServices();
            Cursor cursor = dbHelper.consultarDatos();
            arrayList = productosServices.cursorToArray(cursor);
            if (arrayList.size() == 0) {
                dbFirebase.sincronizarDatos(dbHelper, arrayList);
            }
        } catch (Exception e) {
            Log.e("Database", e.toString());
        }

        imgMaterial = findViewById(R.id.imgMaterial);
        imgHerramientas = findViewById(R.id.imgHerramienta);
        imgElectricos = findViewById(R.id.imgCables);
        txtMaterial = findViewById(R.id.txtMate);
        txtHerramienta = findViewById(R.id.txtHerram);
        txtElectrico = findViewById(R.id.txtElect);

        txtMaterial.setOnClickListener(this::pasarProducto);

        txtHerramienta.setOnClickListener(this::pasarProducto);

        txtElectrico.setOnClickListener(this::pasarProducto);

        imgMaterial.setOnClickListener(this::pasarProducto);

        imgHerramientas.setOnClickListener(this::pasarProducto);

        imgElectricos.setOnClickListener(this::pasarProducto);
    }

    public void pasarProducto(View view) {
        Intent intent = new Intent(getApplicationContext(), Productos.class);
        intent.putExtra("imageTitulo", R.drawable.ferresix);
        intent.putExtra("imageCarrito", R.drawable.carrito);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.geo) {
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}