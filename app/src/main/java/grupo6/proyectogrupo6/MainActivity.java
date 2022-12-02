package grupo6.proyectogrupo6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public ImageButton imgMaterial;
    public ImageButton imgHerramientas;
    public ImageButton imgElectricos;
    public TextView txtMaterial;
    public TextView txtHerramienta;
    public TextView txtElectrico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgMaterial = findViewById(R.id.imgMaterial);
        imgHerramientas = findViewById(R.id.imgHerramienta);
        imgElectricos = findViewById(R.id.imgCables);
        txtMaterial = findViewById(R.id.txtMate);
        txtHerramienta = findViewById(R.id.txtHerram);
        txtElectrico = findViewById(R.id.txtElect);

        txtMaterial.setOnClickListener(this::pasarProducto);

        txtHerramienta.setOnClickListener(this::pasarProducto);

        txtElectrico.setOnClickListener(this::pasarProducto);

        imgMaterial.setOnClickListener(this::pasarProducto);

        imgHerramientas.setOnClickListener(this::pasarProducto);

        imgElectricos.setOnClickListener(this::pasarProducto);
    }

    public void pasarProducto(View view) {
        Intent intent = new Intent(getApplicationContext(), Productos.class);
        intent.putExtra("imageTitulo", R.drawable.ferresix);
        intent.putExtra("imageCarrito", R.drawable.carrito);
        startActivity(intent);
    }

}