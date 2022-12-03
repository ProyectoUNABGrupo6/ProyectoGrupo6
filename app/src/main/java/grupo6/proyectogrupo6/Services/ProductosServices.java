package grupo6.proyectogrupo6.Services;

import android.database.Cursor;

import java.util.ArrayList;

import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.R;


public class ProductosServices {

    public ArrayList<Producto> cursorToArray(Cursor cursor){
        ArrayList<Producto> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else {
            while (cursor.moveToNext()){
                Producto producto = new Producto(
                        R.drawable.cableselectricos,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                );
                list.add(producto);
            }
        }
        return list;
    }
}
