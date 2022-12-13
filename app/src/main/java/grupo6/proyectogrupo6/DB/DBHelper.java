package grupo6.proyectogrupo6.DB;

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
                "id VARCHAR," +
                "NOMBRE VARCHAR," +
                "DESCRIPCION VARCHAR," +
                "PRECIO INTEGER" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
    }

    //CRUD

    public void insetarDatos(String id, String NOMBRE, String DESCRIPCION, int PRECIO) {

        String sql = "INSERT INTO PRODUCTOS VALUES(?, ?, ?, ?)";
        SQLiteStatement statement = FerreteriaDB.compileStatement(sql);

        statement.bindString(1, id);
        statement.bindString(2, NOMBRE);
        statement.bindString(3, DESCRIPCION);
        statement.bindLong(4, PRECIO);
        //statement.bindBlob(4, IMAGEN);

        statement.executeInsert();
        statement.close();


    }

    public Cursor consultarDatos() {
        return FerreteriaDB.rawQuery("SELECT * FROM PRODUCTOS", null);
    }

    public Cursor consultarDatosporID(int id) {
        return FerreteriaDB.rawQuery("SELECT * FROM PRODUCTOS WHERE id = " + id, null);
    }

    public void eliminarDatos(String id) {
        FerreteriaDB.execSQL("DELETE  FROM PRODUCTOS WHERE id =" + id);
        FerreteriaDB.execSQL("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'PRODUCTOS'");
        //FerreteriaDB.close();
    }

    public void actualizarDatos(String id, String NOMBRE, String DESCRIPCION, int PRECIO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE", NOMBRE);
        contentValues.put("DESCRIPCION", DESCRIPCION);
        contentValues.put("PRECIO", PRECIO);
        //contentValues.put("IMAGEN", IMAGEN);

        FerreteriaDB.update("PRODUCTOS", contentValues, "id = ?", new String[]{String.valueOf(id)});
    }

}
