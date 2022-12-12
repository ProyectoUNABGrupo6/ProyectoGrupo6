package grupo6.proyectogrupo6.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import grupo6.proyectogrupo6.entity.Product;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM Product")
    List<Product> findAll();
    @Query("SELECT * FROM PRODUCT WHERE uid= :id")
    Product findById(Long id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Product... entities);
    @Update
    void update(Product... entities);
    @Delete
    void delete(Product... entities);
    //---
    @Query("SELECT * FROM Product ORDER BY name ASC")
    LiveData<List<Product>> findAllOrderByNameAsc();


}
