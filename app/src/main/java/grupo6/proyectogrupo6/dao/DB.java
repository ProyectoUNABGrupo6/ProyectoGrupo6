package grupo6.proyectogrupo6.dao;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import grupo6.proyectogrupo6.entity.Category;
import grupo6.proyectogrupo6.entity.Product;

@Database(entities = {Category.class,Product.class}, version = 1, exportSchema = false)
@TypeConverters({ConvertersDao.class})
public abstract class DB extends RoomDatabase {

    private static volatile DB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract CategoryDao categoryDao();
    public abstract ProductDao productDao();

    public static DB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DB.class, "mintic_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CategoryDao categoryDao = INSTANCE.categoryDao();
                categoryDao.deleteAll();
                ProductDao productDao = INSTANCE.productDao();
                productDao.deleteAll();

                Product p = new Product("Product 1","Product 1", "1");
                productDao.save(p);
            });
        }
    };
}
