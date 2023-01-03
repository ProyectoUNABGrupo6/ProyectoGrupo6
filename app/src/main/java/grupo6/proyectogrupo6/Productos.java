package grupo6.proyectogrupo6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import grupo6.proyectogrupo6.Adapters.ProductoAdapters;
import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Entities.Usuario;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class Productos extends AppCompatActivity {

    public DBHelper dbHelper;
    public DBFirebase dbFirebase;
    public ProductosServices productosServices;
    public FloatingActionButton botonAtras, botonCarritoPr;
    public TextView usuarioP, txtIDUserP;
    public View view;
    public ListView listViewProductos;
    public ArrayList<Producto> arrayList;
    public ArrayList<Usuario> arrayUsuario;
    public ProductoAdapters productoAdapters;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        botonAtras = findViewById(R.id.imgAtras);
        botonCarritoPr = findViewById(R.id.btnCarritoP);
        usuarioP = findViewById(R.id.txtUsuP);
        txtIDUserP = findViewById(R.id.txtIdUserP);
        Bundle bundle = getIntent().getExtras();
        arrayList = new ArrayList<>();

        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();
            arrayUsuario = new ArrayList<>();
            productosServices = new ProductosServices();
            String categoria = bundle.getString("categoria");
            Cursor cursor = dbHelper.consultarDatosCategoria(categoria);
            Cursor cursor1 = dbHelper.consultarUsuarios();
            arrayUsuario = productosServices.cursorUsuario(cursor1);
            arrayList = productosServices.cursorToArray(cursor);
            productoAdapters = new ProductoAdapters(this, arrayList, arrayUsuario);
            listViewProductos = findViewById(R.id.listViewProductos);
            listViewProductos.setAdapter(productoAdapters);


        } catch (Exception e) {
            Log.e("Database", e.toString());
        }

        dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.consultarUsuarios();
        arrayUsuario = productosServices.cursorUsuario(cursor);
        if (arrayUsuario.size() != 0) {
            int posicion = 0;
            Usuario usuario = arrayUsuario.get(posicion);
            String user = usuario.getEmail();
            int id = usuario.getIdUser();
            usuarioP.setText(user);
            txtIDUserP.setText(String.valueOf(id));
        }

        botonAtras.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        String user = usuarioP.getText().toString();
        if (!user.isEmpty()) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            menu.findItem(R.id.actionAdd).setVisible(true);
            menu.findItem(R.id.exit).setVisible(true);
            menu.findItem(R.id.menuLogin).setVisible(false);
            invalidateOptionsMenu();
        } else {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuLogin) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.exit) {
            int id = Integer.parseInt(txtIDUserP.getText().toString());
            dbHelper.eliminarUsuario(id);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.actionAdd) {
            Intent intent = new Intent(getApplicationContext(), AgregarProducto.class);
            Bundle bundle = getIntent().getExtras();
            String cat = bundle.getString("categoria");
            intent.putExtra("categoria", cat);
            intent.putExtra("usuario", usuarioP.getText().toString());
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}