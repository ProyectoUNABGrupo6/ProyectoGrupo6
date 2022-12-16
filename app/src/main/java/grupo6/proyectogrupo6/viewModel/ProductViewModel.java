package grupo6.proyectogrupo6.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.repository.GenericEntityManagerRepository;
import grupo6.proyectogrupo6.repository.ProductRepository;

public class ProductViewModel extends GenericEntityManagerViewModel<Product,ProductRepository> {

    public ProductViewModel(@NonNull Application application) {
        super(application);
    }
    @Override
    public ProductRepository builderRepository(@NonNull Application application) {
        return new ProductRepository(application);
    }

}
