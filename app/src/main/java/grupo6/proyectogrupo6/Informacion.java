package grupo6.proyectogrupo6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Usuario;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class Informacion extends AppCompatActivity {


    private ProductosServices productosServices;
    private DBHelper dbHelper;
    public ImageButton botonAtras;
    public ImageView imgTitulo, imgCarrito;
    public ImageButton imgProd;
    public TextView txtTituloInf, txtDescripInf, txtPrecioInf, txtID, txtUsuI, txtCatInf;
    public FloatingActionButton botonEliminar, botonActualizar;
    public ArrayList<Usuario> arrayUsuario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        arrayUsuario = new ArrayList<>();

        dbHelper = new DBHelper(this);
        txtID = findViewById(R.id.txtID);
        botonAtras = findViewById(R.id.imgAtrasProd);
        imgTitulo = findViewById(R.id.imgTituloProd);
        imgCarrito = findViewById(R.id.imgCarritoInfo);
        imgProd = findViewById(R.id.imgProductoInfo);
        txtTituloInf = findViewById(R.id.txtTituloProInfo);
        txtDescripInf = findViewById(R.id.txtDescripInfo);
        txtPrecioInf = findViewById(R.id.txtPrecioInfo);
        botonActualizar = findViewById(R.id.botonActualizar);
        botonEliminar = findViewById(R.id.botonEliminar);
        txtUsuI = findViewById(R.id.txtUsuI);
        txtCatInf = findViewById(R.id.txtCategoriaInf);



        if (arrayUsuario.size() != 0) {

            int posicion = 0;

            Usuario usuario = arrayUsuario.get(posicion);
            int id = usuario.getIdUser();
            String user = usuario.getEmail();

            txtUsuI.setText(user);
        }


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgTit = bundle.getInt("imageTitulo");
            int imgCar = bundle.getInt("imageCarrito");
            int imgatras = bundle.getInt("imageAtras");
            String usuario = bundle.getString("usuario");
            imgTitulo.setImageResource(imgTit);
            imgCarrito.setImageResource(imgCar);
            botonAtras.setImageResource(imgatras);
            txtUsuI.setText(usuario);

            String id = bundle.getString("ida");
            //byte[] byteArray = getIntent().getByteArrayExtra("imageCode");
            //Bitmap imgPro = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            String tituloInf = bundle.getString("titulo");
            String DescripInf = bundle.getString("descripcion");
            String categoriaInf = bundle.getString("categoria");
            String precInf = bundle.getString("precio");
            txtID.setText(id);
            //imgProd.setImageBitmap(imgPro);
            txtTituloInf.setText(tituloInf);
            txtDescripInf.setText(DescripInf);
            txtCatInf.setText(categoriaInf);
            txtPrecioInf.setText(precInf);
        }

        botonEliminar.setOnClickListener(View -> {
            String id = txtID.getText().toString().trim();
            dbHelper.eliminarDatos(id);
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);
            startActivity(intent);
        });

        botonActualizar.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), AgregarProducto.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);

            productosServices = new ProductosServices();
            //byte[] byteArray = productosServices.imageButtonToByte(imgProd);

            intent.putExtra("id", txtID.getText());
            //intent.putExtra("imageCode", byteArray);
            intent.putExtra("titulo", txtTituloInf.getText());
            intent.putExtra("descripcion", txtDescripInf.getText());
            intent.putExtra("precio", txtPrecioInf.getText());
            startActivity(intent);

        });


        botonAtras.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            intent.putExtra("categoria", txtCatInf.getText());
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);
            startActivity(intent);
        });

    }
}