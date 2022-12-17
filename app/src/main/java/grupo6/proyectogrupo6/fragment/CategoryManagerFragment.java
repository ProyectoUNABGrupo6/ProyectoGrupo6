package grupo6.proyectogrupo6.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.entity.Category;
import grupo6.proyectogrupo6.viewModel.CategoryViewModel;

public class CategoryManagerFragment extends GenericEntityManagerEditFragment<Category, CategoryViewModel>{

    private TextView name;
    private TextView description;

    @Override
    public Class<Category> getTypeClass() {
        return Category.class;
    }
    @Override
    public int getLayout() {
        return R.layout.category_manager_fragment;
    }
    @Override
    public int getImageView() {
        return R.id.menuItemCategoryAddImg;
    }
    @Override
    public int getAddImageButton() {
        return R.id.menuItemCategoryAddImgButton;
    }
    @Override
    public int getSaveButton() {
        return R.id.menuItemCategorySaveButton;
    }
    @Override
    public int getDeleteButton() {
        return R.id.menuItemCategoryDeleteButton;
    }
    @Override
    public int getNavigationManagerFragment() {
        return R.id.action_menuItemCategoryAdd_to_menuItemCategory;
    }
    @Override
    public void bindFields(@NonNull View v) {
        name = v.findViewById(R.id.menuItemCategoryAddName);
        description = v.findViewById(R.id.menuItemCategoryAddDescription);
    }
    @Override
    public void initDataFields(View v, Category data) {
        if(data == null) return;
        setImage(data.getUrlImage());
        name.setText(data.getName());
        description.setText(data.getDescription());
    }
    @Override
    public String getErrorDataFields(@NonNull Category data) {
        if(data.getUrlImage().isEmpty()) return "Empty photo";
        if(data.getName() == null) return "Empty name";
        return null;
    }
    @Override
    public CategoryViewModel constructViewModel() {
        return new ViewModelProvider(this).get(CategoryViewModel.class);
    }
    @Override
    public Category getInstanceData() {
        return new Category();
    }
    @Override
    public void updateData(@NonNull Category data) {
        data.setUrlImage(getUriImage());
        data.setName(getStringField(name.getText()));
        data.setDescription(getStringField(description.getText()));
    }
}

