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
    private TextView txtMaterial;
    private TextView txtHerramienta;
    private TextView txtElectrico;


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

        txtMaterial.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            startActivity(intent);

        });

        txtHerramienta.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            startActivity(intent);

        });

        txtElectrico.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            startActivity(intent);

        });
        
        imgMaterial.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            startActivity(intent);

        });

        imgHerramientas.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), Productos.class);
            startActivity(intent);

        });

        imgElectricos.setOnClickListener(View -> {

            Intent intent = new Intent(getApplicationContext(), Productos.class);
            startActivity(intent);
        });
    }

}