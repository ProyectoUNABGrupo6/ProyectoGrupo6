package grupo6.proyectogrupo6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Informacion extends AppCompatActivity {

    public ImageButton botonAtras;
    public ImageView imgTitulo;
    public ImageView imgCarrito;
    public ImageView imgProd;
    public TextView txtTituloInf;
    public TextView txtDescripInf;
    public TextView txtPrecioInf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        botonAtras = findViewById(R.id.imgAtrasForm);
        imgTitulo = findViewById(R.id.imgTituloForm);
        imgCarrito = findViewById(R.id.imgCarritoInfo);
        imgProd = findViewById(R.id.imgProductoInfo);
        txtTituloInf = findViewById(R.id.txtTituloProInfo);
        txtDescripInf = findViewById(R.id.txtDescripInfo);
        txtPrecioInf = findViewById(R.id.txtPrecioInfo);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgTit = bundle.getInt("imageTitulo");
            int imgCar = bundle.getInt("imageCarrito");
            int imgatras = bundle.getInt("imageAtras");
            imgTitulo.setImageResource(imgTit);
            imgCarrito.setImageResource(imgCar);
            botonAtras.setImageResource(imgatras);

            byte[] byteArray = getIntent().getByteArrayExtra("imageCode");
            Bitmap imgPro = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            String tituloInf = bundle.getString("titulo");
            String DescripInf = bundle.getString("descripcion");
            String precInf = "$" + String.valueOf(bundle.getInt("precio"));
            imgProd.setImageBitmap(imgPro);
            txtTituloInf.setText(tituloInf);
            txtDescripInf.setText(DescripInf);
            txtPrecioInf.setText(precInf);
        }

        botonAtras.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);
            startActivity(intent);
        });

    }
}