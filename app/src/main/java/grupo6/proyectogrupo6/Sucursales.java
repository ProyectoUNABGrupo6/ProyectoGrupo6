package grupo6.proyectogrupo6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Sucursales extends AppCompatActivity {

    public ImageButton btnBogNor, btnBogSur, btnBogOccident, btnCali, btnMonter, btnMedallo;
    public TextView textBogNor, textBogSur, textBogOccident, textMedallo, textCali, textMonter;
    public ImageButton btnAtras;
    public ImageView titulo;
    public TextView user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales);

        btnBogNor = findViewById(R.id.btnBogNor);
        btnBogOccident = findViewById(R.id.btnBogOccident);
        btnBogSur = findViewById(R.id.btnBogSur);
        btnCali = findViewById(R.id.btnCali);
        btnMedallo = findViewById(R.id.btnMedallo);
        btnMonter = findViewById(R.id.btnMonter);
        textBogNor = findViewById(R.id.textBogNor);
        textBogSur = findViewById(R.id.textBogSur);
        textBogOccident = findViewById(R.id.textBogOccident);
        textCali = findViewById(R.id.textCali);
        textMonter = findViewById(R.id.textMonter);
        textMedallo = findViewById(R.id.textMedallo);

        btnAtras = findViewById(R.id.imgAtrasSuc);
        titulo = findViewById(R.id.imgTituloSuc);
        user = findViewById(R.id.txtUsuSuc);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int imgTitle = bundle.getInt("imageTitulo");
            int imgAtras = bundle.getInt("imageAtras");
            String users = bundle.getString("usuario");
            btnAtras.setImageResource(imgAtras);
            titulo.setImageResource(imgTitle);
            user.setText(users);
        }

        btnAtras.setOnClickListener(View ->{
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            startActivity(intent);
        });

        btnBogNor.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("Latitud", 4.7476942628995005);
            intent.putExtra("Longitud", -74.03228450928715);
            startActivity(intent);
        });

        btnBogOccident.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("Latitud", 4.722294357510393);
            intent.putExtra("Longitud", -74.12098141830825);
            startActivity(intent);
        });

        btnBogSur.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("Latitud", 4.595078876663067);
            intent.putExtra("Longitud", -74.18063815574894);
            startActivity(intent);
        });

        btnCali.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("Latitud", 3.440599261934281);
            intent.putExtra("Longitud", -76.51442170143127);
            startActivity(intent);
        });

        btnMonter.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("Latitud", 8.742695533487847);
            intent.putExtra("Longitud", -75.88017046151569);
            startActivity(intent);
        });

        btnMedallo.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Maps.class);
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("Latitud", 6.250928279740549);
            intent.putExtra("Longitud", -75.582515001297);
            startActivity(intent);
        });


    }
}