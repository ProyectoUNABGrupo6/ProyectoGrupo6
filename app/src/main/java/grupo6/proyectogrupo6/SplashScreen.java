package grupo6.proyectogrupo6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Categoria;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Services.ProductosServices;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    public DBHelper dbHelper;
    public DBFirebase dbFirebase;
    public ProductosServices productosServices;
    public ArrayList<Producto> arrayList;
    public ArrayList<Categoria> arrayCategoria;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        arrayList = new ArrayList<>();
        arrayCategoria = new ArrayList<>();

        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();
            productosServices = new ProductosServices();
            Cursor cursor = dbHelper.consultarCategorias();
            Cursor cursor1 = dbHelper.consultarDatos();
            arrayCategoria = productosServices.cursorCategoria(cursor);
            arrayList = productosServices.cursorToArray(cursor1);
            if (arrayList.size() == 0 && arrayCategoria.size() == 0) {
                dbFirebase.buscarCategorias(dbHelper, arrayCategoria);
                dbFirebase.buscarDatos(dbHelper, arrayList);
            }
        } catch (Exception e) {
            Log.e("Database", e.toString());
        }

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);

        }, 4000);
    }
}