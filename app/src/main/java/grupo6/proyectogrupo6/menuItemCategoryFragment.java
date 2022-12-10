package grupo6.proyectogrupo6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class menuItemCategoryFragment extends Fragment
                                      implements SearchView.OnQueryTextListener,
                                                 View.OnClickListener {

    private SearchView svCategory;
    //Category
    private RecyclerView rvCategory;
    private CardItemCategoryAdapter categoryAdapter;
    //add button
    private FloatingActionButton addButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_item_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Search
        initSearch(view);
        //Category
        initRvCategory(view);
        initAdapterRvCategory();
        loadDataRvCategory();
        //add button
        initAddButton(view);
    }

    //Search
    private void initSearch(View v){
        svCategory = v.findViewById(R.id.menuItemCategorySvCategory);
        svCategory.setOnQueryTextListener(this);
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }
    @Override
    public boolean onQueryTextChange(String s) {
        categoryAdapter.filter(s);
        return false;
    }
    //Category
    private void initRvCategory(View v){
        rvCategory = v.findViewById(R.id.menuItemCategoryRvCategory);
        rvCategory.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
    }
    private void initAdapterRvCategory(){
        categoryAdapter = new CardItemCategoryAdapter();
        rvCategory.setAdapter(categoryAdapter);

    }
    private void loadDataRvCategory(){
        List<CardItemCategoryModel> list = new ArrayList<>();
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 1", "Description 1"));
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 2", "Description 2"));
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 3", "Description 3"));
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 4", "Description 4"));
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 5", "Description 5"));
        categoryAdapter.updateList(list);
    }
    //add button
    private void initAddButton(View v){
        addButton = v.findViewById(R.id.menuItemCategoryAddButton);
        addButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.menuItemCategoryAddButton){
            navigate(v,R.id.menuItemCategoryAdd);
        }
    }

//    public void navActivity(View v, Activity activity){
//        Intent intent = new Intent(v.getContext(), activity.getClass());
//        startActivity(intent);
//    }
//
//    public void navFragment( int layout, Fragment fragment){
//        FragmentManager fragmentManager =  getParentFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(layout, fragment)
//                .setReorderingAllowed(true)
//                .addToBackStack(fragment.getClass().getName())// name can be null
//                .commit();
//    }

    public void navigate(View v, int idFragment){
        Navigation.findNavController(v).navigate(idFragment);
    }

}