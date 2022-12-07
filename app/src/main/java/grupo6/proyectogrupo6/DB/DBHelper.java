package grupo6.proyectogrupo6.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase FerreteriaDB;

    public DBHelper(Context context) {
        super(context, "FerreteriaDB.db", null, 1);
        FerreteriaDB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase FerreteriaDB) {

        FerreteriaDB.execSQL("CREATE TABLE PRODUCTOS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE VARCHAR," +
                "DESCRIPCION VARCHAR," +
                "PRECIO INTEGER," +
                "IMAGEN BLOB" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
    }

    //CRUD

    public void insetarDatos(String NOMBRE, String DESCRIPCION, int PRECIO, byte[] IMAGEN) {

        String sql = "INSERT INTO PRODUCTOS VALUES(null, ?, ?, ?, ?)";
        SQLiteStatement statement = FerreteriaDB.compileStatement(sql);
        statement.bindString(1, NOMBRE);
        statement.bindString(2, DESCRIPCION);
        statement.bindLong(3, PRECIO);
        statement.bindBlob(4, IMAGEN);

        statement.executeInsert();
        statement.close();


    }

    public Cursor consultarDatos() {
        Cursor cursor = FerreteriaDB.rawQuery("SELECT * FROM PRODUCTOS", null);
        return cursor;
    }

}
