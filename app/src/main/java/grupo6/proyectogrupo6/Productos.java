package grupo6.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Productos extends AppCompatActivity {

    public Button producto1;
    public Button producto2;
    public Button producto3;
    public Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        producto1 = findViewById(R.id.botonProducto1);
        producto2 = findViewById(R.id.botonProducto2);
        producto3 = findViewById(R.id.botonProducto3);
        cerrar = findViewById(R.id.botonCerrar);

        producto1.setOnClickListener(view -> {
            Intent intent = new Intent(Productos.this, Informacion.class);
            String cantidad = "10.000";
            String precio = "$1.000";
            intent.putExtra("image", R.drawable.fresa);
            intent.putExtra("pre", precio);
            intent.putExtra("cant", cantidad);

            startActivity(intent);
        });
        producto2.setOnClickListener(view -> {
            Intent intent = new Intent(Productos.this, Informacion.class);
            String cantidad = "5.000";
            String precio = "$2.000";
            intent.putExtra("image", R.drawable.mango);
            intent.putExtra("pre", precio);
            intent.putExtra("cant", cantidad);

            startActivity(intent);
        });
        producto3.setOnClickListener(view -> {
            Intent intent = new Intent(Productos.this, Informacion.class);
            String cantidad = "50.000";
            String precio = "$500";
            intent.putExtra("image", R.drawable.frutita);
            intent.putExtra("pre", precio);
            intent.putExtra("cant", cantidad);

            startActivity(intent);
        });

        cerrar.setOnClickListener(this::cerrarSesion);


    }


    public void cerrarSesion(View view) {
        Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}