package grupo6.proyectogrupo6.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import grupo6.proyectogrupo6.entity.Category;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> findAll();
    @Query("SELECT * FROM Category WHERE uid= :id")
    Category findById(Long id);
    @Query("SELECT * FROM Category WHERE name LIKE :name ")
    LiveData<List<Category>> findByNameLike(String name);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Category... entities);
    @Update
    void update(Category... entities);
    @Delete
    void delete(Category... entities);
    //---
    @Query("SELECT * FROM Category ORDER BY name ASC")
    LiveData<List<Category>> findAllOrderByNameAsc();
    @Query("DELETE FROM Category")
    void deleteAll();


}
