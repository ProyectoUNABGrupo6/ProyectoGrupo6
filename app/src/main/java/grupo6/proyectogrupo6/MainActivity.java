package grupo6.proyectogrupo6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import grupo6.proyectogrupo6.Adapters.CategoriaAdapters;
import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Categoria;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Entities.Usuario;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class MainActivity extends AppCompatActivity {

    public DBHelper dbHelper;
    public DBFirebase dbFirebase;
    public ProductosServices productosServices;
    public ArrayList<Producto> arrayList;
    public ArrayList<Categoria> arrayCategoria;
    public ArrayList<Usuario> arrayUsuario;
    public CategoriaAdapters categoriaAdapters;
    public ListView listViewCategoria;
    public TextView txtUsuario;
    public TextView txtIdUser;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        arrayCategoria = new ArrayList<>();
        arrayUsuario = new ArrayList<>();

        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();
            productosServices = new ProductosServices();
            Cursor cursor = dbHelper.consultarCategorias();
            Cursor cursor1 = dbHelper.consultarDatos();
            Cursor cursor3 = dbHelper.consultarUsuarios();
            arrayUsuario = productosServices.cursorUsuario(cursor3);
            arrayCategoria = productosServices.cursorCategoria(cursor);
            arrayList = productosServices.cursorToArray(cursor1);
            categoriaAdapters = new CategoriaAdapters(this, arrayCategoria, arrayUsuario);
            listViewCategoria = findViewById(R.id.listViewCategorias);
            listViewCategoria.setAdapter(categoriaAdapters);
        } catch (Exception e) {
            Log.e("Database", e.toString());
        }

        dbHelper = new DBHelper(this);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtIdUser = findViewById(R.id.txtIdUser);
        Cursor cursor = dbHelper.consultarUsuarios();
        arrayUsuario = productosServices.cursorUsuario(cursor);
        if (arrayUsuario.size() != 0) {
            int posicion = 0;
            Usuario usuario = arrayUsuario.get(posicion);
            int id = usuario.getIdUser();
            String user = usuario.getEmail();
            txtUsuario.setText(user);
            txtIdUser.setText(String.valueOf(id));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        String user = txtUsuario.getText().toString();
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
            int id = Integer.parseInt(txtIdUser.getText().toString());
            dbHelper.eliminarUsuario(id);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.actionAdd) {
            Intent intent = new Intent(getApplicationContext(), AgregarCategoria.class);
            intent.putExtra("usuario", txtUsuario.getText().toString());
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.geo) {
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}