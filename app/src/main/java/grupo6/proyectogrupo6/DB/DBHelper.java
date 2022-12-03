package grupo6.proyectogrupo6.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "Ferreteria", null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE PRODUCTOS(" +
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

    public void insetarDatos(String NOMBRE, String DESCRIPCION, int PRECIO, byte[] IMAGEN){

        String sql = "INSERT INTO PRODUCTOS VALUES(null, ?, ?, ?, ?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindString(1, NOMBRE);
        statement.bindString(2, DESCRIPCION);
        statement.bindLong(3, PRECIO);
        statement.bindBlob(4, IMAGEN);

        statement.executeInsert();
    }

    public Cursor consultarDatos(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTOS",  null);
        return cursor;
    }

}
