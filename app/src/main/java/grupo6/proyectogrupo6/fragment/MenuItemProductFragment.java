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
import grupo6.proyectogrupo6.adapter.CardItemProductAdapter;
import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.viewModel.ProductViewModel;

public class MenuItemProductFragment extends Fragment  implements View.OnClickListener {

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
        initAddButton(view);
    }

    //Product
    private void initAdapterRvProduct(View v){
        productAdapter = new CardItemProductAdapter(new CardItemProductAdapter.ProductDiff(), new CardItemProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                navigate(v,R.id.menuItemProductAdd,product);
            }
        });
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
    //add button
    private void initAddButton(View v){
        addButton = v.findViewById(R.id.menuItemProductAddButton);
        addButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.menuItemProductAddButton){
            navigate(v,R.id.menuItemProductAdd,null);
        }
    }

    public void navigate(View v, int idFragment, Product product){
        Bundle bundle = new Bundle();
        if(product != null) {
            bundle.putString("name",product.getName());
            bundle.putString("description",product.getDescription());
            bundle.putString("price",product.getPrice());
        }
        Navigation.findNavController(v).navigate(idFragment,bundle);
    }
}