package grupo6.proyectogrupo6.Services;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Categoria;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Entities.Usuario;


public class ProductosServices {

    public DBHelper dbHelper;
    public DBFirebase dbFirebase;
    public ProductosServices productosServices;
    public ArrayList<Producto> arrayList;
    public ArrayList<Categoria> arrayCategoria;

    public ArrayList<Producto> cursorToArray(Cursor cursor) {
        ArrayList<Producto> list = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return list;
        } else {
            while (cursor.moveToNext()) {
                Producto producto = new Producto(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(8),
                        cursor.getInt(3),
                        cursor.getString(4),
                        Boolean.valueOf(cursor.getString(5)),
                        formatoFecha(cursor.getString(6)),
                        formatoFecha(cursor.getString(7))
                );
                list.add(producto);

            }
        }
        return list;
    }

    public ArrayList<Categoria> cursorCategoria(Cursor cursor) {
        ArrayList<Categoria> list = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return list;
        } else {
            while ((cursor.moveToNext())) {
                Categoria categoria = new Categoria(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );

                list.add(categoria);
            }
        }
        return list;
    }

    public ArrayList<Usuario> cursorUsuario(Cursor cursor) {
        ArrayList<Usuario> list = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return list;
        } else {
            while (cursor.moveToNext()) {
                Usuario usuario = new Usuario(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)

                );
                list.add(usuario);
            }
        }
        return list;
    }


    public Date formatoFecha(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertarImagen(String url, ImageButton imageButton, Context context) {
        Glide.with(context)
                .load(url)
                .override(500, 500)
                .into(imageButton);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void sincronizarDB(Context context) {
        arrayList = new ArrayList<>();
        arrayCategoria = new ArrayList<>();

        try {
            dbHelper = new DBHelper(context);
            dbFirebase = new DBFirebase();
            productosServices = new ProductosServices();
            Cursor cursor = dbHelper.consultarCategorias();
            Cursor cursor1 = dbHelper.consultarDatos();
            arrayCategoria = productosServices.cursorCategoria(cursor);
            arrayList = productosServices.cursorToArray(cursor1);
            if (arrayList.size() == 0 && arrayCategoria.size() == 0) {
                dbFirebase.buscarCategorias(dbHelper, arrayCategoria);
                dbFirebase.buscarDatos(dbHelper, arrayList);
            }
        } catch (Exception e) {
            Log.e("Database", e.toString());
        }
    }
}
