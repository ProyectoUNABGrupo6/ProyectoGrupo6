package grupo6.proyectogrupo6.DB;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class DBFirebase {

    private final FirebaseFirestore db;

    public DBFirebase(Context context) {
        this.db = FirebaseFirestore.getInstance();
    }

    public void insertarDatos(String nombre, String descripcion, int precio, byte[] image){
        Map<String, Object> PRODUCTO = new HashMap<>();
        PRODUCTO.put("NOMBRE", nombre);
        PRODUCTO.put("DESCRIPCION", descripcion);
        PRODUCTO.put("PRECIO", precio);
        //PRODUCTOS.put("IMAGEN", image);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            db.collection("PRODUCTOS")
                    .add(PRODUCTO)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
        }


    }

    public void buscarDatos() {

        db.collection("PRODUCTOS")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        }
                    }else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }


}
