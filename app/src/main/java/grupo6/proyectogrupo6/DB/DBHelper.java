package grupo6.proyectogrupo6.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class DBHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase FerreteriaDB;
    private ProductosServices productosServices;

    public DBHelper(Context context) {
        super(context, "FerreteriaDB.db", null, 1);
        FerreteriaDB = this.getWritableDatabase();
        this.productosServices = new ProductosServices();
    }

    @Override
    public void onCreate(SQLiteDatabase FerreteriaDB) {

        FerreteriaDB.execSQL("CREATE TABLE PRODUCTOS(" +
                "id VARCHAR PRIMARY KEY," +
                "NOMBRE VARCHAR," +
                "DESCRIPCION VARCHAR," +
                "PRECIO INTEGER," +
                "IMAGEN VARCHAR," +
                "F_ELIMINAR BOOLEAN," +
                "F_CREACION TEXT," +
                "F_ACTUALIZADO TEXT" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
    }

    //CRUD

    public void insetarDatos(Producto producto) {

        String sql = "INSERT INTO PRODUCTOS VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = FerreteriaDB.compileStatement(sql);

        statement.bindString(1, producto.getId());
        statement.bindString(2, producto.getNombre());
        statement.bindString(3, producto.getDescripcion());
        statement.bindLong(4, producto.getPrecio());
        statement.bindString(5, producto.getImagen());
        statement.bindString(6, String.valueOf(producto.isEliminado()));
        statement.bindString(7, String.valueOf(producto.getCreado()));
        statement.bindString(8, String.valueOf(producto.getActualizacion()));

        statement.executeInsert();
        //statement.close();


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

    public void actualizarDatos(String id, String NOMBRE, String DESCRIPCION, int PRECIO, String IMAGEN) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE", NOMBRE);
        contentValues.put("DESCRIPCION", DESCRIPCION);
        contentValues.put("PRECIO", PRECIO);
        contentValues.put("IMAGEN", IMAGEN);

        FerreteriaDB.update("PRODUCTOS", contentValues, "id = ?", new String[]{String.valueOf(id)});
    }

}
