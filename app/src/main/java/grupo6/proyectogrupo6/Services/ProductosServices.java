package grupo6.proyectogrupo6.Services;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import grupo6.proyectogrupo6.Entities.Producto;


public class ProductosServices {

    public ArrayList<Producto> cursorToArray(Cursor cursor) {
        ArrayList<Producto> list = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return list;
        } else {
            while (cursor.moveToNext()) {
                Producto producto = new Producto(
                        cursor.getBlob(4),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                );
                list.add(producto);
            }
        }
        return list;
    }


    public byte[] imageButtonToByte(ImageButton imageButton) {

        Bitmap bitmap = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        return stream.toByteArray();
    }




}
