package grupo6.proyectogrupo6.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import grupo6.proyectogrupo6.Entities.Categoria;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Entities.Usuario;
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

        FerreteriaDB.execSQL("CREATE TABLE CATEGORIAS(" +
                "idCat VARCHAR PRIMARY KEY," +
                "CATEGORIA VARCHAR," +
                "IMAGEN VARCHAR" +
                ")");


        FerreteriaDB.execSQL("CREATE TABLE PRODUCTOS(" +
                "id VARCHAR PRIMARY KEY," +
                "NOMBRE VARCHAR," +
                "DESCRIPCION VARCHAR," +
                "PRECIO INTEGER," +
                "IMAGEN VARCHAR," +
                "F_ELIMINAR BOOLEAN," +
                "F_CREACION TEXT," +
                "F_ACTUALIZADO TEXT," +
                "CATEGORIA VARCHAR" +
                ")");

        FerreteriaDB.execSQL("CREATE TABLE USUARIOS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "EMAIL VARCHAR," +
                "CONTRASEÑA VARCHAR" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CATEGORIAS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USUARIOS");
    }

    //CRUD

    public void insertarCategorias(Categoria categoria) {
        String sql = "INSERT INTO CATEGORIAS VALUES(?, ?, ?)";
        SQLiteStatement statement = FerreteriaDB.compileStatement(sql);

        statement.bindString(1, categoria.getIdCat());
        statement.bindString(2, categoria.getCategoria());
        statement.bindString(3, categoria.getImagen());
        statement.executeInsert();
    }

    public void insertarUsuarios(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS VALUES(null, ?, ?)";
        SQLiteStatement statement = FerreteriaDB.compileStatement(sql);


        statement.bindString(1, usuario.getEmail());
        statement.bindString(2, usuario.getContra());
        statement.executeInsert();
    }

    public void insertarDatos(Producto producto) {

        String sql = "INSERT INTO PRODUCTOS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = FerreteriaDB.compileStatement(sql);

        statement.bindString(1, producto.getId());
        statement.bindString(2, producto.getNombre());
        statement.bindString(3, producto.getDescripcion());
        statement.bindLong(4, producto.getPrecio());
        statement.bindString(5, producto.getImagen());
        statement.bindString(6, String.valueOf(producto.isEliminado()));
        statement.bindString(7, String.valueOf(producto.getCreado()));
        statement.bindString(8, String.valueOf(producto.getActualizacion()));
        statement.bindString(9, producto.getCategoria());

        statement.executeInsert();
        //statement.close();


    }

    public Cursor consultarUsuarios() {
        return FerreteriaDB.rawQuery("SELECT * FROM USUARIOS", null);
    }

    public Cursor consultarDatos() {
        return FerreteriaDB.rawQuery("SELECT * FROM PRODUCTOS", null);
    }

    public Cursor consultarCategorias() {
        return FerreteriaDB.rawQuery("SELECT * FROM CATEGORIAS", null);
    }

    public Cursor consultarDatosCategoria(String categoria) {
        return FerreteriaDB.rawQuery("SELECT * FROM PRODUCTOS WHERE CATEGORIA = '" + categoria + "'" , null);
    }

    public void eliminarDatos(String id) {
        FerreteriaDB.execSQL("DELETE  FROM PRODUCTOS WHERE id = '" + id + "'");

    }

    public void eliminarCategoria(String id) {
        FerreteriaDB.execSQL("DELETE  FROM CATEGORIAS WHERE id = '" + id + "'");

    }

    public void eliminarUsuario(int id) {
        FerreteriaDB.execSQL("DELETE FROM USUARIOS WHERE id =" + id);
    }

    public void actualizarDatos(String id, String NOMBRE, String DESCRIPCION, int PRECIO, String IMAGEN, String CATEGORIA) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE", NOMBRE);
        contentValues.put("DESCRIPCION", DESCRIPCION);
        contentValues.put("PRECIO", PRECIO);
        contentValues.put("IMAGEN", IMAGEN);
        contentValues.put("CATEGORIA", CATEGORIA);

        FerreteriaDB.update("PRODUCTOS", contentValues, "id = ?", new String[]{String.valueOf(id)});
    }

    public void actualizarCategorias(String id, String CATEGORIA, String IMAGEN) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CATEGORIA", CATEGORIA);
        contentValues.put("IMAGEN", IMAGEN);

        FerreteriaDB.update("CATEGORIAS", contentValues, "id = ?", new String[]{String.valueOf(id)});
    }

}
