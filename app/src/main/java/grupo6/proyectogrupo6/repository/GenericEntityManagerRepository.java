package grupo6.proyectogrupo6.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface GenericEntityManagerRepository<E> {
    public LiveData<List<E>> findAll();
    public LiveData<List<E>> findByIdentifierLike(String identifier);
    public void save(E entity);
    public void update(E entity);
    public void delete(E entity);

}
