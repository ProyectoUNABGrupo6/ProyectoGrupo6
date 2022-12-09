package grupo6.proyectogrupo6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class menuItemCategoryFragment extends Fragment {

    //Category
    private RecyclerView rvCategory;
    private CardItemCategoryAdapter categoryAdapter;

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
        //Category
        init(view);
        initAdapter();
        loadData();

    }
    //Category
    private void init(View v){
        rvCategory = v.findViewById(R.id.menuItemCategoryRvCategory);
        rvCategory.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
    }
    private void initAdapter(){
        categoryAdapter = new CardItemCategoryAdapter();
        rvCategory.setAdapter(categoryAdapter);

    }
    private void loadData(){
        List<CardItemCategoryModel> list = new ArrayList<>();
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 1", "Description 1"));
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 2", "Description 2"));
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 3", "Description 3"));
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 4", "Description 4"));
        list.add(new CardItemCategoryModel(R.drawable.img_logo, "category 5", "Description 5"));
        categoryAdapter.updateList(list);
    }
}