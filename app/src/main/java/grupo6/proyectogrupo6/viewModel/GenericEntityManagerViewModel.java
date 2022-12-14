package grupo6.proyectogrupo6.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import grupo6.proyectogrupo6.repository.GenericEntityManagerRepository;


public abstract class GenericEntityManagerViewModel<E,R extends GenericEntityManagerRepository> extends AndroidViewModel {

    public abstract R builderRepository();
    public abstract R getRepository();

    private final R r;
    private final LiveData<List<E>> entities;

    public GenericEntityManagerViewModel(@NonNull Application application) {
        super(application);
        r = getRepository();
        entities = getRepository().findAll();
    }
    public LiveData<List<E>> getEntities() {
        return this.entities;
    }
    public LiveData<List<E>> getEntitiesByIdentifierLike(String identifier) {
        return getRepository().findByIdentifierLike(identifier);
    }
    public void save(E entity) {
        getRepository().save(entity);
    }
    public void update(E entity) {
        getRepository().update(entity);
    }
    public void delete(E entity) {
        getRepository().delete(entity);
    }

}
