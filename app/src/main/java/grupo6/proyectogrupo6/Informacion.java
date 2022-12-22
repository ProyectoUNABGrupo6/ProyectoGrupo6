package grupo6.proyectogrupo6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Usuario;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class Informacion extends AppCompatActivity {

    private ProductosServices productosServices;
    private DBHelper dbHelper;
    private DBFirebase dbFirebase;
    public ImageButton imgProd;
    public TextView txtTituloInf, txtDescripInf, txtPrecioInf, txtID, txtUsuI, txtCatInf;
    public FloatingActionButton botonEliminar, botonActualizar, botonAtras, botonCarrito;
    public ArrayList<Usuario> arrayUsuario;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        productosServices = new ProductosServices();

        Intent intentIn = getIntent();

        arrayUsuario = new ArrayList<>();
        dbHelper = new DBHelper(this);
        dbFirebase = new DBFirebase();
        botonAtras = findViewById(R.id.btnAtrasInfo);
        botonCarrito = findViewById(R.id.btnCarritoInfo);
        txtID = findViewById(R.id.txtID);
        imgProd = findViewById(R.id.imgProductoInfo);
        txtTituloInf = findViewById(R.id.txtTituloProInfo);
        txtDescripInf = findViewById(R.id.txtDescripInfo);
        txtPrecioInf = findViewById(R.id.txtPrecioInfo);
        botonActualizar = findViewById(R.id.botonActualizar);
        botonEliminar = findViewById(R.id.botonEliminar);
        txtUsuI = findViewById(R.id.txtUsuI);
        txtCatInf = findViewById(R.id.txtCategoriaInf);

        txtID.setText(intentIn.getStringExtra("ida"));
        productosServices.insertarImagen(intentIn.getStringExtra("imagen"), imgProd, Informacion.this);
        txtTituloInf.setText(intentIn.getStringExtra("titulo"));
        txtDescripInf.setText(intentIn.getStringExtra("descripcion"));
        txtCatInf.setText(intentIn.getStringExtra("categoria"));
        txtPrecioInf.setText("$" + intentIn.getStringExtra("precio"));

        if (arrayUsuario.size() != 0) {
            int posicion = 0;
            Usuario usuario = arrayUsuario.get(posicion);
            String user = usuario.getEmail();
            txtUsuI.setText(user);
        }

        botonEliminar.setOnClickListener(View -> {
            String id = txtID.getText().toString().trim();
            String categoria = txtCatInf.getText().toString();
            dbHelper.eliminarDatos(id);
            dbFirebase.eliminarDatos(id);

            Intent intent = new Intent(getApplicationContext(), Productos.class);
            intent.putExtra("categoria", categoria);
            startActivity(intent);
        });
        botonActualizar.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), AgregarProducto.class);
            intent.putExtra("id", txtID.getText());
            intent.putExtra("imagen", intentIn.getStringExtra("imagen"));
            intent.putExtra("titulo", txtTituloInf.getText());
            intent.putExtra("descripcion", txtDescripInf.getText());
            intent.putExtra("categoria", txtCatInf.getText());
            intent.putExtra("precio", txtPrecioInf.getText());
            startActivity(intent);

        });
        botonAtras.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            intent.putExtra("categoria", txtCatInf.getText());
            startActivity(intent);
        });
    }
}