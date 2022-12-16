package grupo6.proyectogrupo6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;

    public Button btnRegistro;
    public EditText insEmailReg, insContraReg, insConfContra;
    public ImageButton imgReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        btnRegistro = findViewById(R.id.btnRegistroReg);
        insEmailReg = findViewById(R.id.insEmailReg);
        insContraReg = findViewById(R.id.insContrasenaReg);
        insConfContra = findViewById(R.id.insConfirmarContra);
        imgReg = findViewById(R.id.imgReg);

        btnRegistro.setOnClickListener(View -> {
            String email = insEmailReg.getText().toString().trim();
            String contra = insContraReg.getText().toString().trim();
            String configContra = insConfContra.getText().toString().trim();

            if (contra.compareTo(configContra) == 0) {

                mAuth.createUserWithEmailAndPassword(email, contra)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(getApplicationContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                            Log.e("CreacionErradaUser", e.toString());
                        });
            } else {
                Toast.makeText(this, "La contraseña no Coincide", Toast.LENGTH_LONG).show();
            }
        });

    }
}