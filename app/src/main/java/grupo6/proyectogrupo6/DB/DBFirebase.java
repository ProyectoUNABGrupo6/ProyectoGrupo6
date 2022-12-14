package grupo6.proyectogrupo6.DB;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import grupo6.proyectogrupo6.Adapters.ProductoAdapters;
import grupo6.proyectogrupo6.Entities.Producto;

public class DBFirebase {

    private final FirebaseFirestore PRODUCTOS;

    public DBFirebase() {
        this.PRODUCTOS = FirebaseFirestore.getInstance();
    }

    public void insertarDatos(Producto producto) {
        Map<String, Object> PRODUCTO = new HashMap<>();
        PRODUCTO.put("id", producto.getId());
        PRODUCTO.put("NOMBRE", producto.getNombre());
        PRODUCTO.put("DESCRIPCION", producto.getDescripcion());
        PRODUCTO.put("PRECIO", producto.getPrecio());
        PRODUCTO.put("IMAGEN", producto.getImagen());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            PRODUCTOS.collection("PRODUCTOS")
                    .add(PRODUCTO)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void buscarDatos(ProductoAdapters productoAdapters, ArrayList<Producto> list) {

        PRODUCTOS.collection("PRODUCTOS")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());

                            Producto producto = new Producto(
                                    Objects.requireNonNull(document.getData().get("id")).toString(),
                                    Objects.requireNonNull(document.getData().get("NOMBRE")).toString(),
                                    Objects.requireNonNull(document.getData().get("DESCRIPCION")).toString(),
                                    Integer.parseInt(Objects.requireNonNull(document.getData().get("PRECIO")).toString())
                            );
                            list.add(producto);
                        }
                        productoAdapters.notifyDataSetChanged();
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    public void actualizarDatos(String id, String NOMBRE, String DESCRIPCION, int PRECIO) {
        PRODUCTOS.collection("PRODUCTOS")
                .document(id)
                .update(
                        "NOMBRE", NOMBRE,
                        "DESCRIPCION", DESCRIPCION,
                        "PRECIO", PRECIO)
                .addOnCompleteListener(task -> {

                });
    }
    public void eliminarDatos(String id){
        PRODUCTOS.collection("PRODUCTOS")
                .document(id)
                .delete()
                .addOnCompleteListener(task -> {

                });
    }
}
