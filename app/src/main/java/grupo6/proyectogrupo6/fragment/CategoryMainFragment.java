package grupo6.proyectogrupo6.fragment;

import androidx.lifecycle.ViewModelProvider;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.adapter.CategoryRecycleViewAdapter;
import grupo6.proyectogrupo6.adapter.GenericEntityManagerAdapter;
import grupo6.proyectogrupo6.entity.Category;
import grupo6.proyectogrupo6.viewModel.CategoryViewModel;

public class CategoryMainFragment extends GenericEntityManagerFragment<Category, CategoryRecycleViewAdapter, CategoryViewModel> {

    @Override
    public int getLayout() {
        return R.layout.category_main_fragment;
    }
    @Override
    public int getRecycleView() {
        return R.id.menuItemCategoryRvCategory;
    }
    @Override
    public int getSearchView() {
        return R.id.menuItemCategorySvCategory;
    }
    @Override
    public int getNavigationManagerEditFragment() {
        return R.id.menuItemCategoryAdd;
    }
    @Override
    public int getAddItemButton() {
        return R.id.menuItemCategoryAddButton;
    }
    @Override
    public CategoryRecycleViewAdapter getAdapter(GenericEntityManagerAdapter.OnItemClickListener<Category> onItemClickListener) {
        return new CategoryRecycleViewAdapter(onItemClickListener);
    }
    @Override
    public CategoryViewModel constructViewModel() {
        return new ViewModelProvider(this).get(CategoryViewModel.class);
    }
}