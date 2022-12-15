package grupo6.proyectogrupo6.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.RealPathUtil;
import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.viewModel.GenericEntityManagerViewModel;
import grupo6.proyectogrupo6.viewModel.ProductViewModel;

public class ProductManagerFragment extends GenericEntityManagerEditFragment<Product,ProductViewModel>{


    private TextView name;
    private TextView description;
    private TextView price;

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
    public void initDataFields(View v) {
        if(getArguments().getString("name") != null) name.setText(getArguments().getString("name",""));
        if(getArguments().getString("description") != null) description.setText(getArguments().getString("description",""));
        if(getArguments().getString("price") != null) price.setText(getArguments().getString("price",""));
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