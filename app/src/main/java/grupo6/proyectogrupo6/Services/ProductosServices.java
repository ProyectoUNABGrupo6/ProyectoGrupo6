package grupo6.proyectogrupo6.Services;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import grupo6.proyectogrupo6.Entities.Producto;


public class ProductosServices {

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


    public byte[] imageButtonToByte(ImageButton imageButton) {

        Bitmap bitmap = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        return stream.toByteArray();
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

    public String stringaFecha(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return dateFormat.format(date);

    }


}
