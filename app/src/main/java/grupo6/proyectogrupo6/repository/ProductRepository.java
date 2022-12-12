package grupo6.proyectogrupo6.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import grupo6.proyectogrupo6.dao.DB;
import grupo6.proyectogrupo6.dao.ProductDao;
import grupo6.proyectogrupo6.entity.Product;

public class ProductRepository {

    private ProductDao dao;
    private LiveData<List<Product>> allProductOrderByNameAsc;

    public ProductRepository(Application application) {
        DB db = DB.getDatabase(application);
        dao = db.productDao();
        allProductOrderByNameAsc = dao.findAllOrderByNameAsc();
    }

    public LiveData<List<Product>> getAllProductOrderByNameAsc() {
        return allProductOrderByNameAsc;
    }
    public void insert(Product product) {
        DB.databaseWriteExecutor.execute(() -> {
            dao.save(product);
        });
    }
}
