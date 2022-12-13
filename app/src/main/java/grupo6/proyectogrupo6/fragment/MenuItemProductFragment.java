package grupo6.proyectogrupo6.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.adapter.CardItemCategoryAdapter;
import grupo6.proyectogrupo6.adapter.CardItemProductAdapter;
import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.model.CardItemCategoryModel;
import grupo6.proyectogrupo6.viewModel.ProductViewModel;

public class MenuItemProductFragment extends Fragment {

    private SearchView svProduct;
    //Product
    private RecyclerView rvProduct;
    private CardItemProductAdapter productAdapter;
    private ProductViewModel  productViewModel;
    //add button
    private FloatingActionButton addButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_item_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Search
        //initSearch(view);
        //Product
        initAdapterRvProduct(view);
        initRvProduct(view);
        initViewModelProduct(view);
        //loadDataRvCategory();
        //add button
        //initAddButton(view);
    }

    //Product
    private void initAdapterRvProduct(View v){
        productAdapter = new CardItemProductAdapter(new CardItemProductAdapter.ProductDiff());
    }
    private void initRvProduct(View v){
        rvProduct = v.findViewById(R.id.menuItemProductRvProduct);
        rvProduct.setAdapter(productAdapter);
        rvProduct.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
    }
    private void initViewModelProduct(View v){
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProductOrderByNameAsc().observe(getViewLifecycleOwner(), list -> {
            // Update the cached copy of the words in the adapter.
            productAdapter.submitList(list);
        });
    }
}