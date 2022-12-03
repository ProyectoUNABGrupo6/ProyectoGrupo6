package grupo6.proyectogrupo6;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AgregarProducto extends AppCompatActivity {

    public ImageButton botonAtrasForm;
    public ImageView imgTituloForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        botonAtrasForm = findViewById(R.id.imgAtrasForm);
        imgTituloForm = findViewById(R.id.imgTituloForm);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgTit = bundle.getInt("imageTitulo");
            int imgAtras = bundle.getInt("imageAtras");
            botonAtrasForm.setImageResource(imgAtras);
            imgTituloForm.setImageResource(imgTit);
        }

        botonAtrasForm.setOnClickListener(this::volverAtras);

    }

    public void volverAtras(View view){
        Intent intent = new Intent(getApplicationContext(), Productos.class);
        intent.putExtra("imageAtras", R.mipmap.atras);
        intent.putExtra("imageTitulo", R.drawable.ferresix);
        intent.putExtra("imageCarrito", R.drawable.carrito);
        startActivity(intent);
    }
}