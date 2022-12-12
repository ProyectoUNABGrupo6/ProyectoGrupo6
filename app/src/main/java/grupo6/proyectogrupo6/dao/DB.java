package grupo6.proyectogrupo6.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import grupo6.proyectogrupo6.entity.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {

    private static volatile DB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract ProductDao getProductDao();

    static DB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DB.class, "mintic_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
