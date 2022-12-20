package grupo6.proyectogrupo6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
    public ImageButton botonAtras;
    public ImageView imgTitulo;
    public ImageView imgCarrito;
    public TextView usuarioP;
    public View view;
    public LayoutInflater layoutInflater;


    public ListView listViewProductos;
    public ArrayList<Producto> arrayList;
    public ArrayList<Usuario> arrayUsuario;
    public ProductoAdapters productoAdapters;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        layoutInflater = LayoutInflater.from(this);
        view = layoutInflater.inflate(R.layout.produtos_template, null);
        botonAtras = findViewById(R.id.imgAtras);
        imgTitulo = findViewById(R.id.imgTituloProductoTemplate);
        imgCarrito = findViewById(R.id.imgCarritoProductos);
        usuarioP = findViewById(R.id.txtUsuP);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgTit = bundle.getInt("imageTitulo");
            int imgCar = bundle.getInt("imageCarrito");
            imgTitulo.setImageResource(imgTit);
            imgCarrito.setImageResource(imgCar);

        }


  /*      Spinner spinnerMenu = view.findViewById(R.id.spinnerMenu);
        if (!usuarioS.isEmpty()) {
            spinnerMenu.setVisibility(View.VISIBLE);

        }
*/

        arrayList = new ArrayList<>();

        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();

            productosServices = new ProductosServices();

            String categoria = bundle.getString("categoria");
            Cursor cursor = dbHelper.consultarDatosCategoria(categoria);
            //Cursor cursor1 = dbHelper.consultarDatos();

            arrayList = productosServices.cursorToArray(cursor);


            productoAdapters = new ProductoAdapters(this, arrayList);

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
            int id = usuario.getIdUser();
            String user = usuario.getEmail();

            usuarioP.setText(user);
        }


        botonAtras.setOnClickListener(View -> {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });


        //dbFirebase.buscarDatos(productoAdapters, arrayList);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        String user = usuarioP.getText().toString();
        if (!user.isEmpty()) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);

            menu.findItem(R.id.actionAdd).setVisible(true);
            menu.findItem(R.id.menuLogin).setVisible(false);
            invalidateOptionsMenu();

        } else {

            getMenuInflater().inflate(R.menu.menu, menu);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.actionAdd) {
            Intent intent = new Intent(getApplicationContext(), AgregarProducto.class);
            intent.putExtra("usuario", usuarioP.getText().toString());
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}