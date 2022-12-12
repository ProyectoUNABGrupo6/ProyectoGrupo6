package grupo6.proyectogrupo6.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase FerreteriaDB;

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
        return FerreteriaDB.rawQuery("SELECT * FROM PRODUCTOS", null);
    }

    public Cursor consultarDatosporID(int id) {
        return FerreteriaDB.rawQuery("SELECT * FROM PRODUCTOS WHERE id = " + id, null);
    }

    public void eliminarDatos(int id) {
        FerreteriaDB.execSQL("DELETE  FROM PRODUCTOS WHERE id =" + id);
        FerreteriaDB.execSQL("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'PRODUCTOS'");
        //FerreteriaDB.close();
    }

    public void actualizarDatos(String id, String NOMBRE, String DESCRIPCION, int PRECIO, byte[] IMAGEN) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE", NOMBRE);
        contentValues.put("DESCRIPCION", DESCRIPCION);
        contentValues.put("PRECIO", PRECIO);
        contentValues.put("IMAGEN", IMAGEN);

        FerreteriaDB.update("PRODUCTOS", contentValues, "id = ?", new String[]{String.valueOf(id)});
    }

}
