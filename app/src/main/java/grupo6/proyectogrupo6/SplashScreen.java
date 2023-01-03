package grupo6.proyectogrupo6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import grupo6.proyectogrupo6.Services.ProductosServices;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    public ProductosServices productosServices;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        productosServices = new ProductosServices();
        productosServices.sincronizarDB(this);

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);

        }, 4000);
    }
}