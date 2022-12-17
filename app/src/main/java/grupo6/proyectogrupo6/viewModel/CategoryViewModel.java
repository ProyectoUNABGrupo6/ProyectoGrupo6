package grupo6.proyectogrupo6.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import grupo6.proyectogrupo6.entity.Category;
import grupo6.proyectogrupo6.repository.CategoryRepository;

public class CategoryViewModel extends GenericEntityManagerViewModel<Category, CategoryRepository> {

    public CategoryViewModel(@NonNull Application application) {
        super(application);
    }
    @Override
    public CategoryRepository builderRepository(@NonNull Application application) {
        return new CategoryRepository(application);
    }

}
