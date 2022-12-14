package grupo6.proyectogrupo6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Usuario;

public class Login extends AppCompatActivity {


    public FloatingActionButton btnIngresar, btnRegistrar;
    public EditText insEmail, insContra;
    public ImageButton imgLogin;

    private FirebaseAuth mAuth;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistro);
        insEmail = findViewById(R.id.insEmail);
        insContra = findViewById(R.id.insContrasena);
        imgLogin = findViewById(R.id.imgLogin);

        mAuth = FirebaseAuth.getInstance();

        dbHelper = new DBHelper(this);

        btnIngresar.setOnClickListener(View -> {
            String email = insEmail.getText().toString().trim();
            String contra = insContra.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(email, contra)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Usuario usuario = new Usuario(
                                    email,
                                    contra
                            );
                            dbHelper.insertarUsuarios(usuario);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                        Log.e("ErrorLogin", e.toString());
                    });
        });
        btnRegistrar.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), Registro.class);
            startActivity(intent);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}