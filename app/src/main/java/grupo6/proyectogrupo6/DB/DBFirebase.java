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
import grupo6.proyectogrupo6.Entities.Categoria;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class DBFirebase {


    private final FirebaseFirestore FERRETERIADB;
    private ProductosServices productosServices;

    public DBFirebase() {
        this.FERRETERIADB = FirebaseFirestore.getInstance();
        this.productosServices = new ProductosServices();
    }

    public void insertarDatos(Producto producto) {
        Map<String, Object> PRODUCTOS = new HashMap<>();
        PRODUCTOS.put("id", producto.getId());
        PRODUCTOS.put("NOMBRE", producto.getNombre());
        PRODUCTOS.put("DESCRIPCION", producto.getDescripcion());
        PRODUCTOS.put("PRECIO", producto.getPrecio());
        PRODUCTOS.put("IMAGEN", producto.getImagen());
        PRODUCTOS.put("F_ELIMINADO", producto.isEliminado());
        PRODUCTOS.put("F_CREADO", producto.getCreado());
        PRODUCTOS.put("F_ACTUALIZADO", producto.getActualizacion());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            FERRETERIADB.collection("PRODUCTOS")
                    .add(PRODUCTOS)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
        }


    }

    public void insertarCategorias(Categoria categoria) {
        Map<String, Object> CATEGORIAS = new HashMap<>();
        CATEGORIAS.put("idCat", categoria.getIdCat());
        CATEGORIAS.put("CATEGORIA", categoria.getCategoria());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            FERRETERIADB.collection("CATEGORIAS")
                    .add(CATEGORIAS)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void buscarDatos(ProductoAdapters productoAdapters, ArrayList<Producto> list) {

        FERRETERIADB.collection("PRODUCTOS")
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
                                        document.getData().get("CATEGORIA").toString(),
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
    public void sincronizarDatos(DBHelper dbHelper, ArrayList<Producto> listProd, ArrayList<Categoria> listCat) {

        FERRETERIADB.collection("PRODUCTOS")
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
                                        document.getData().get("CATEGORIA").toString(),
                                        Integer.parseInt((document.getData().get("PRECIO")).toString()),
                                        document.getData().get("IMAGEN").toString(),
                                        Boolean.valueOf(document.getData().get("F_ELIMINADO").toString()),
                                        document.getDate("F_CREADO"),
                                        document.getDate("F_ACTUALIZADO")
                                );
                                listProd.add(producto);

                                dbHelper.insertarDatos(producto);

                            }
                        }
                        for (QueryDocumentSnapshot document : task.getResult()){
                            Log.d(TAG, document.getId() + " => " + document.getData());

                            Categoria categoria = new Categoria(
                                    document.getData().get("idCat").toString(),
                                    document.getData().get("CATEGORIA").toString()
                            );
                            listCat.add(categoria);
                            dbHelper.insertarCategorias(categoria);
                        }

                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    public void actualizarDatos(String id, String NOMBRE, String DESCRIPCION, int PRECIO, String IMAGEN) {
        FERRETERIADB.collection("PRODUCTOS")
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
        FERRETERIADB.collection("PRODUCTOS")
                .document(id)
                .delete()
                .addOnCompleteListener(task -> {

                });
    }
}
