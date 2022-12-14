package grupo6.proyectogrupo6.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import grupo6.proyectogrupo6.model.MenuItemCategoryAddModel;

public class CategoryManagerFragment extends Fragment
                                         implements View.OnClickListener{

    //add photo
    private FloatingActionButton addPhotoButton;
    private ImageView imgCategory;
    private ActivityResultLauncher<String> mGetContent;
    //other other fields
    private TextView nameCategory;
    private TextView shortDescriptionCategory;
    //save info
    private FloatingActionButton addInfoButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_manager_fragment, container, false);
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
        addPhotoButton = v.findViewById(R.id.menuItemCategoryAddImgButton);
        addPhotoButton.setOnClickListener(this);
        // photo
        imgCategory = v.findViewById(R.id.menuItemCategoryAddImg);
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
            case R.id.menuItemCategoryAddImgButton:
                chooseFile();
                break;
            case R.id.menuItemCategorySaveButton:
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
        imgCategory.setTag(uri.getPath());
        imgCategory.setImageURI(uri);
    }
    //other other fields
    private void initOtherFields(View v){
        nameCategory = v.findViewById(R.id.menuItemCategoryAddName);
        shortDescriptionCategory = v.findViewById(R.id.menuItemCategoryAddDescription);
    }
    private void initOtherFieldsData(View v) {
        if(getArguments() == null){
            Toast toast = Toast.makeText(v.getContext(), "bundle == null", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if(getArguments().getString("name") != null) nameCategory.setText(getArguments().getString("name",""));
        if(getArguments().getString("description") != null) shortDescriptionCategory.setText(getArguments().getString("description",""));
    }
    //save info
    private void initSaveInfo(View v){
        addInfoButton = v.findViewById(R.id.menuItemCategorySaveButton);
        addInfoButton.setOnClickListener(this);
    }
    private void saveInfo(View v){
        MenuItemCategoryAddModel infoCategory;
        try {
            errorFields();
            infoCategory = getInfo();
            Log.i("Save",infoCategory.toString());
            navigate(v,R.id.action_menuItemCategoryAdd_to_menuItemCategory);
        } catch (Exception e) {
            Toast toast = Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    private MenuItemCategoryAddModel getInfo() {
        MenuItemCategoryAddModel infoCategory = new MenuItemCategoryAddModel();
        infoCategory.setUrlPhoto(getString(imgCategory.getTag()));
        infoCategory.setName(getString(nameCategory.getText()));
        infoCategory.setShortDescription(getString(shortDescriptionCategory.getText()));
        return infoCategory;
    }
    public void navigate(View v, int idFragment){
        Navigation.findNavController(v).navigate(idFragment);
    }
    private void errorFields() throws Exception{
        if(imgCategory.getTag() == null) throw new Exception("Empty photo");
        if(nameCategory.getText() == null) throw new Exception("Empty name");
    }
    private String getString(Object field){
        if(field == null) return "";
        return field.toString();
    }
}

