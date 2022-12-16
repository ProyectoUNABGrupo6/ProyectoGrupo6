package grupo6.proyectogrupo6.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.viewModel.ProductViewModel;

public class ProductManagerFragment extends GenericEntityManagerEditFragment<Product,ProductViewModel>{


    private TextView name;
    private TextView description;
    private TextView price;

    @Override
    public Class<Product> getTypeClass() {
        return Product.class;
    }
    @Override
    public int getLayout() {
        return R.layout.product_manager_fragment;
    }
    @Override
    public int getImageView() {
        return R.id.menuItemProductAddImg;
    }
    @Override
    public int getAddImageButton() {
        return R.id.menuItemProductAddImgButton;
    }
    @Override
    public int getSaveButton() {
        return R.id.menuItemProductSaveButton;
    }
    @Override
    public int getNavigationManagerFragment() {
        return R.id.action_menuItemProductAdd_to_menuItemProduct;
    }
    @Override
    public void bindFields(@NonNull View v) {
        name = v.findViewById(R.id.menuItemProductAddName);
        description = v.findViewById(R.id.menuItemProductAddDescription);
        price = v.findViewById(R.id.menuItemProductAddPrice);
    }
    @Override
    public void initDataFields(View v,Product entity) {
        if(entity == null) return;
        name.setText(entity.getName());
        description.setText(entity.getDescription());
        price.setText(entity.getPrice());
    }
    @Override
    public String getErrorDataFields(@NonNull Product data) {
        if(data.getUrlImage().isEmpty()) return "Empty photo";
        if(data.getName() == null) return "Empty name";
        return null;
    }
    @Override
    public ProductViewModel constructViewModel() {
        return new ViewModelProvider(this).get(ProductViewModel.class);
    }
    @Override
    public Product getDataFields() {
        Product data = new Product();
        data.setUrlImage(getUriImage());
        data.setName(getStringField(name.getText()));
        data.setDescription(getStringField(description.getText()));
        data.setPrice(getStringField(price.getText()));
        return data;
    }
}