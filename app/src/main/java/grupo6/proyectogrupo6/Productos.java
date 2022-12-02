package grupo6.proyectogrupo6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import grupo6.proyectogrupo6.Adapters.ProductoAdapters;
import grupo6.proyectogrupo6.Entities.Producto;

public class Productos extends AppCompatActivity {

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


        arrayList = new ArrayList<>();


        Producto producto1 = new Producto(R.drawable.mango, "Producto1", "Descripcion1", 1000);
        Producto producto2 = new Producto(R.drawable.fresa, "Producto2", "Descripcion2", 2000);
        Producto producto3 = new Producto(R.drawable.frutita, "Producto3", "Descripcion3", 5000);
        Producto producto4 = new Producto(R.drawable.herramienta, "Producto4", "Descripcion4", 4000);
        Producto producto5 = new Producto(R.drawable.cableselectricos, "Producto5", "Descripcion5", 10000);
        Producto producto6 = new Producto(R.drawable.carrito, "Producto6", "Descripcion6", 50000);

        arrayList.add(producto1);
        arrayList.add(producto2);
        arrayList.add(producto3);
        arrayList.add(producto4);
        arrayList.add(producto5);
        arrayList.add(producto6);


        productoAdapters = new ProductoAdapters(getApplicationContext(), arrayList);

        listViewProductos = findViewById(R.id.listViewProductos);
        listViewProductos.setAdapter(productoAdapters);


    }
}