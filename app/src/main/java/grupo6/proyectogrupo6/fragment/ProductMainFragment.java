package grupo6.proyectogrupo6.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.adapter.GenericEntityManagerAdapter;
import grupo6.proyectogrupo6.adapter.ProductRecycleViewAdapter;
import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.viewModel.ProductViewModel;

public class ProductMainFragment extends GenericEntityManagerFragment<Product,ProductRecycleViewAdapter,ProductViewModel> {


    @Override
    public int getLayout() {
        return R.layout.product_main_fragment;
    }
    @Override
    public int getRecycleView() {
        return R.id.menuItemProductRvProduct;
    }
    @Override
    public int getSearchView() {
        return R.id.menuItemProductSvProduct;
    }
    @Override
    public int getNavigationManagerFragment() {
        return R.id.menuItemProductAdd;
    }
    @Override
    public int getAddItemButton() {
        return R.id.menuItemProductAddButton;
    }
    @Override
    public void buildBundle(@NonNull Bundle bundle, @NonNull Product entity) {
        bundle.putString("name",entity.getName());
        bundle.putString("description",entity.getDescription());
        bundle.putString("price",entity.getPrice());
    }
    @Override
    public ProductRecycleViewAdapter getAdapter(GenericEntityManagerAdapter.OnItemClickListener<Product> onItemClickListener) {
        return new ProductRecycleViewAdapter(onItemClickListener);
    }
    @Override
    public ProductViewModel constructViewModel() {
        return new ViewModelProvider(this).get(ProductViewModel.class);
    }
}