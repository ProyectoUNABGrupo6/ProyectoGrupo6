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

import grupo6.proyectogrupo6.Adapters.ProductoAdapters;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class DBFirebase {


    private final FirebaseFirestore PRODUCTOS;
    private ProductosServices productosServices;

    public DBFirebase() {
        this.PRODUCTOS = FirebaseFirestore.getInstance();
        this.productosServices = new ProductosServices();
    }

    public void insertarDatos(Producto producto) {
        Map<String, Object> PRODUCTO = new HashMap<>();
        PRODUCTO.put("id", producto.getId());
        PRODUCTO.put("NOMBRE", producto.getNombre());
        PRODUCTO.put("DESCRIPCION", producto.getDescripcion());
        PRODUCTO.put("PRECIO", producto.getPrecio());
        PRODUCTO.put("IMAGEN", producto.getImagen());
        PRODUCTO.put("F_ELIMINADO", producto.isEliminado());
        PRODUCTO.put("F_CREADO", producto.getCreado());
        PRODUCTO.put("F_ACTUALIZADO", producto.getActualizacion());


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

                            Producto producto;
                            if (!Boolean.valueOf(document.getData().get("F_ELIMINADO").toString())) {
                                producto = new Producto(
                                        document.getData().get("id").toString(),
                                        (document.getData().get("NOMBRE")).toString(),
                                        (document.getData().get("DESCRIPCION")).toString(),
                                        Integer.parseInt((document.getData().get("PRECIO")).toString()),
                                        document.getData().get("IMAGEN").toString(),
                                        Boolean.valueOf(document.getData().get("F_ELIMINADO").toString()),
                                        productosServices.formatoFecha(document.getData().get("F_CREADO").toString()),
                                        productosServices.formatoFecha(document.getData().get("F_ACTUALIZADO").toString())
                                );
                                list.add(producto);
                            }
                        }
                        productoAdapters.notifyDataSetChanged();
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void sincronizarDatos(DBHelper dbHelper, ArrayList<Producto> list) {

        PRODUCTOS.collection("PRODUCTOS")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());

                            Producto producto;
                            if (!Boolean.valueOf(document.getData().get("F_ELIMINADO").toString())) {
                                producto = new Producto(
                                        document.getData().get("id").toString(),
                                        document.getData().get("NOMBRE").toString(),
                                        document.getData().get("DESCRIPCION").toString(),
                                        Integer.parseInt((document.getData().get("PRECIO")).toString()),
                                        document.getData().get("IMAGEN").toString(),
                                        Boolean.valueOf(document.getData().get("F_ELIMINADO").toString()),
                                        document.getDate("F_CREADO"),
                                        document.getDate("F_ACTUALIZADO")
                                );
                                list.add(producto);

                                dbHelper.insetarDatos(producto);

                            }
                        }

                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    public void actualizarDatos(String id, String NOMBRE, String DESCRIPCION, int PRECIO, String IMAGEN) {
        PRODUCTOS.collection("PRODUCTOS")
                .document(id)
                .update(
                        "NOMBRE", NOMBRE,
                        "DESCRIPCION", DESCRIPCION,
                        "PRECIO", PRECIO,
                        "IMAGEN", IMAGEN)
                .addOnCompleteListener(task -> {

                });
    }

    public void eliminarDatos(String id) {
        PRODUCTOS.collection("PRODUCTOS")
                .document(id)
                .delete()
                .addOnCompleteListener(task -> {

                });
    }
}
