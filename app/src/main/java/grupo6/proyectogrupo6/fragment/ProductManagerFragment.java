package grupo6.proyectogrupo6.fragment;

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
import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.viewModel.ProductViewModel;

public class ProductManagerFragment extends Fragment
                                        implements View.OnClickListener{

    //add photo
    private FloatingActionButton addPhotoButton;
    private ImageView img;
    private ActivityResultLauncher<String> mGetContent;
    //other other fields
    private TextView name;
    private TextView description;
    private TextView price;
    //save info
    private FloatingActionButton addInfoButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_manager_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //add photo button
        initSelectPhoto(view);
        initOtherFields(view);
        initOtherFieldsData(view);
        initSaveInfo(view);
    }
    //add photo button
    private void initSelectPhoto(View v) {

        //button
        addPhotoButton = v.findViewById(R.id.menuItemProductAddImgButton);
        addPhotoButton.setOnClickListener(this);
        // photo
        img = v.findViewById(R.id.menuItemProductAddImg);
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        if(uri != null) setUriPhoto(uri);
                    }
                });
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.menuItemProductAddImgButton:
                chooseFile();
                break;
            case R.id.menuItemProductSaveButton:
                saveInfo(v);
                break;
            default:
                break;
        }

    }

    //add photo
    private void chooseFile() {
        mGetContent.launch("image/*");
    }
    private void setUriPhoto(Uri uri){
        img.setTag(uri.getPath());
        img.setImageURI(uri);
    }
    //other other fields
    private void initOtherFields(View v){
        name = v.findViewById(R.id.menuItemProductAddName);
        description = v.findViewById(R.id.menuItemProductAddDescription);
        price = v.findViewById(R.id.menuItemProductAddPrice);
    }
    private void initOtherFieldsData(View v) {
        if(getArguments() == null){
            Toast toast = Toast.makeText(v.getContext(), "bundle == null", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if(getArguments().getString("name") != null) name.setText(getArguments().getString("name",""));
        if(getArguments().getString("description") != null) description.setText(getArguments().getString("description",""));
        if(getArguments().getString("price") != null) price.setText(getArguments().getString("price",""));
    }
    //save info
    private void initSaveInfo(View v){
        addInfoButton = v.findViewById(R.id.menuItemProductSaveButton);
        addInfoButton.setOnClickListener(this);
    }
    private void saveInfo(View v){
        Product infoProduct;
        try {
            errorFields();
            infoProduct = getInfo();
            Log.i("Save",infoProduct.toString());
            saveData(infoProduct);
            navigate(v,R.id.action_menuItemProductAdd_to_menuItemProduct);
        } catch (Exception e) {
            Toast toast = Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    private Product getInfo() {
        Product infoProduct = new Product();
        infoProduct.setUrlImage(getString(img.getTag()));
        infoProduct.setName(getString(name.getText()));
        infoProduct.setDescription(getString(description.getText()));
        infoProduct.setPrice(getString(price.getText()));
        return infoProduct;
    }
    public void navigate(View v, int idFragment){
        Navigation.findNavController(v).navigate(idFragment);
    }
    private void errorFields() throws Exception{
        if(img.getTag() == null) throw new Exception("Empty photo");
        if(name.getText() == null) throw new Exception("Empty name");
    }
    private String getString(Object field){
        if(field == null) return "";
        return field.toString();
    }
    //---
    public void saveData(Product data){
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.insert(data);
    }
}