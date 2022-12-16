package grupo6.proyectogrupo6.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import grupo6.proyectogrupo6.dao.DB;
import grupo6.proyectogrupo6.dao.ProductDao;
import grupo6.proyectogrupo6.entity.Product;

public class ProductRepository implements GenericEntityManagerRepository<Product>{

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

    @Override
    public LiveData<List<Product>> findAll() {
        return dao.findAll();
    }
    @Override
    public LiveData<List<Product>> findByIdentifierLike(String identifier) {
        String search = "%" + identifier + "%";
        return dao.findByNameLike(search);
    }
    @Override
    public void save(Product entity) {
        DB.databaseWriteExecutor.execute(() -> {
            dao.save(entity);
        });
    }
    @Override
    public void update(Product entity) {
        DB.databaseWriteExecutor.execute(() -> {
            dao.update(entity);
        });
    }
    @Override
    public void delete(Product entity) {
        DB.databaseWriteExecutor.execute(() -> {
            dao.delete(entity);
        });
    }
}
