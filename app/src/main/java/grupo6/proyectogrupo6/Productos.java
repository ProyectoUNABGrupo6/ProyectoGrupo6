package grupo6.proyectogrupo6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Productos extends AppCompatActivity {

    public ImageButton botonAtras;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        botonAtras = findViewById(R.id.imgAtras);

        botonAtras.setOnClickListener(View ->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });


    }
}