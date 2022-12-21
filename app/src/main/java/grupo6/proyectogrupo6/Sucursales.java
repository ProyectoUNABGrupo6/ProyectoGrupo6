package grupo6.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Sucursales extends AppCompatActivity {

    private ImageButton btnBogNor, btnBogSur, btnBogOccident, btnCali, btnMonter, btnMedallo;
    private TextView textBogNor, textBogSur, textBogOccident, textMedallo, textCali, textMonter, textInitSucur;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales);

        btnBogNor = (ImageButton) findViewById(R.id.btnBogNor);
        btnBogOccident = (ImageButton) findViewById(R.id.btnBogOccident);
        btnBogSur = (ImageButton) findViewById(R.id.btnBogSur);
        btnCali = (ImageButton) findViewById(R.id.btnCali);
        btnMedallo = (ImageButton) findViewById(R.id.btnMedallo);
        btnMonter = (ImageButton) findViewById(R.id.btnMonter);
        textBogNor = (TextView) findViewById(R.id.textBogNor);
        textBogSur = (TextView) findViewById(R.id.textBogSur);
        textBogOccident = (TextView) findViewById(R.id.textBogOccident);
        textCali = (TextView) findViewById(R.id.textCali);
        textMonter = (TextView) findViewById(R.id.textMonter);
        textMedallo = (TextView) findViewById(R.id.textMedallo);
        textInitSucur = (TextView) findViewById(R.id.textInitSucur);

        btnBogNor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Maps.class);
                intent.putExtra("Latitud", 4.7476942628995005);
                intent.putExtra("Longitud", -74.03228450928715);
                startActivity(intent);
            }
        });

        btnBogOccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Maps.class);
                intent.putExtra("Latitud", 4.722294357510393);
                intent.putExtra("Longitud", -74.12098141830825);
                startActivity(intent);
            }
        });

        btnBogSur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Maps.class);
                intent.putExtra("Latitud", 4.595078876663067);
                intent.putExtra("Longitud", -74.18063815574894);
                startActivity(intent);
            }
        });

        btnCali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Maps.class);
                intent.putExtra("Latitud", 3.440599261934281);
                intent.putExtra("Longitud", -76.51442170143127);
                startActivity(intent);
            }
        });

        btnMonter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Maps.class);
                intent.putExtra("Latitud", 8.742695533487847);
                intent.putExtra("Longitud", -75.88017046151569);
                startActivity(intent);
            }
        });

        btnMedallo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Maps.class);
                intent.putExtra("Latitud", 6.250928279740549);
                intent.putExtra("Longitud", -75.582515001297);
                startActivity(intent);
            }
        });


    }
}