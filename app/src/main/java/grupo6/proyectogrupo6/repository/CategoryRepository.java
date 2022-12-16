package grupo6.proyectogrupo6.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import grupo6.proyectogrupo6.dao.CategoryDao;
import grupo6.proyectogrupo6.dao.DB;
import grupo6.proyectogrupo6.entity.Category;

public class CategoryRepository implements GenericEntityManagerRepository<Category>{

    private CategoryDao dao;
    private LiveData<List<Category>> allProductOrderByNameAsc;

    public CategoryRepository(Application application) {
        DB db = DB.getDatabase(application);
        dao = db.categoryDao();
        allProductOrderByNameAsc = dao.findAllOrderByNameAsc();
    }

    public LiveData<List<Category>> getAllProductOrderByNameAsc() {
        return allProductOrderByNameAsc;
    }
    public void insert(Category category) {
        DB.databaseWriteExecutor.execute(() -> {
            dao.save(category);
        });
    }

    @Override
    public LiveData<List<Category>> findAll() {
        return dao.findAll();
    }
    @Override
    public LiveData<List<Category>> findByIdentifierLike(String identifier) {
        String search = "%" + identifier + "%";
        return dao.findByNameLike(search);
    }
    @Override
    public void save(Category entity) {
        DB.databaseWriteExecutor.execute(() -> {
            dao.save(entity);
        });
    }
    @Override
    public void update(Category entity) {
        DB.databaseWriteExecutor.execute(() -> {
            dao.update(entity);
        });
    }
    @Override
    public void delete(Category entity) {
        DB.databaseWriteExecutor.execute(() -> {
            dao.delete(entity);
        });
    }
}
