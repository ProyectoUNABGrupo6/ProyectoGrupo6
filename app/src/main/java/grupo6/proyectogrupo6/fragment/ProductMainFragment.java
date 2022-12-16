package grupo6.proyectogrupo6.fragment;

import androidx.lifecycle.ViewModelProvider;

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
    public int getNavigationManagerEditFragment() {
        return R.id.menuItemProductAdd;
    }
    @Override
    public int getAddItemButton() {
        return R.id.menuItemProductAddButton;
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