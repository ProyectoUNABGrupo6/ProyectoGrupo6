package grupo6.proyectogrupo6.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.repository.ProductRepository;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository r;
    private final LiveData<List<Product>> allProductOrderByNameAsc;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        r = new ProductRepository(application);
        allProductOrderByNameAsc = r.getAllProductOrderByNameAsc();
    }

    public LiveData<List<Product>> getAllProductOrderByNameAsc() {
        return allProductOrderByNameAsc;
    }
    public void insert(Product product) {
        r.insert(product);
    }
}
