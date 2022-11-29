package grupo6.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText contrasena;
    public Button botonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.insUsuario);
        contrasena = findViewById(R.id.insContrasena);
        botonLogin = findViewById(R.id.botonLogin);

        botonLogin.setOnClickListener(this::acceso);


    }

    public void acceso(View view){
            String user = usuario.getText().toString();
            String pass = contrasena.getText().toString();

            if(user.equals("Grupo6") && pass.equals("Grupo6")){
                Toast.makeText(this,"Bievenido",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Productos.class);
                startActivity(intent);
            }else {
                Toast.makeText(this,"Usuario o contraseña invalidos", Toast.LENGTH_LONG).show();
            }
    }
}