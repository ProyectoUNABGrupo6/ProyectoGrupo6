package grupo6.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Informacion extends AppCompatActivity {

    public ImageView imageninfo;
    public TextView precio;
    public TextView cantidad;
    public Button atras;
    //int imgValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        imageninfo = findViewById(R.id.imagenInfo);
        precio = findViewById(R.id.txtValor);
        cantidad = findViewById(R.id.txtCantidad);
        atras = findViewById(R.id.botonAtras);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgValor = bundle.getInt("image");
            String pre = bundle.getString("pre");
            String cant = bundle.getString("cant");
            precio.setText(pre);
            cantidad.setText(cant);
            imageninfo.setImageResource(imgValor);
        }

        atras.setOnClickListener(this::volver);
    }

    public void volver(View view) {
        Intent intent = new Intent(getApplicationContext(), Productos.class);
        startActivity(intent);
    }
}