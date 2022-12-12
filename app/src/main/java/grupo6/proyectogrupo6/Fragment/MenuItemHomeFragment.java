package grupo6.proyectogrupo6.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.Adapter.CardItemRecommendedCategoryAdapter;
import grupo6.proyectogrupo6.Adapter.CardItemRecommendedProductAdapter;
import grupo6.proyectogrupo6.Adapter.MenuItemHomeSliderAdapter;
import grupo6.proyectogrupo6.Model.CardItemRecommendedCategoryModel;
import grupo6.proyectogrupo6.Model.CardItemRecommendedProductModel;
import grupo6.proyectogrupo6.Model.SliderItemImageModel;

public class MenuItemHomeFragment extends Fragment {

    //Slider Image
    private SliderView svm;
    private MenuItemHomeSliderAdapter misa;
    //Recommended category
    private RecyclerView rvCategorias;
    private CardItemRecommendedCategoryAdapter categoryAdapter;
    //Recommended product
    private RecyclerView rvProduct;
    private CardItemRecommendedProductAdapter productAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_item_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Slider Image
        initSliderImage(view);
        initAdapterSliderImage();
        loadDataSliderImage();
        //Recommended category
        initRecommendedCategory(view);
        initAdapterRecommendedCategory();
        loadDataRecommendedCategory();
        //Recommended product
        initRecommendedProduct(view);
        initAdapterRecommendedProduct();
        loadDataRecommendedProduct();
    }
    //Slider Image
    private void initSliderImage(View v){
        svm = v.findViewById(R.id.menuItemHomeSliderImage);
    }
    private void initAdapterSliderImage() {
        misa = new MenuItemHomeSliderAdapter(getContext());
        svm.setSliderAdapter(misa);
        svm.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        svm.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        svm.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        svm.setIndicatorSelectedColor(Color.WHITE);
        svm.setIndicatorUnselectedColor(Color.GRAY);
        svm.setScrollTimeInSec(4); //set scroll delay in seconds :
        svm.startAutoCycle();
    }
    private void loadDataSliderImage() {

        List<SliderItemImageModel> lista = new ArrayList<>();
        lista.add(new SliderItemImageModel(R.drawable.img_logo, "Imagen 1"));
        lista.add(new SliderItemImageModel(R.drawable.img_logo, "Imagen 2"));
        lista.add(new SliderItemImageModel(R.drawable.img_logo, "Imagen 3"));
        lista.add(new SliderItemImageModel(R.drawable.img_logo, "Imagen 4"));
        misa.updateItem(lista);
    }
    //Recommended Category
    private void initRecommendedCategory(View v){
        rvCategorias = v.findViewById(R.id.menuItemHomeRvRecommendedCategory);
        rvCategorias.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));
    }
    private void initAdapterRecommendedCategory(){
        categoryAdapter = new CardItemRecommendedCategoryAdapter();
        rvCategorias.setAdapter(categoryAdapter);

    }
    private void loadDataRecommendedCategory(){
        List<CardItemRecommendedCategoryModel> list = new ArrayList<>();
        list.add(new CardItemRecommendedCategoryModel(R.drawable.img_logo, "Image 1"));
        list.add(new CardItemRecommendedCategoryModel(R.drawable.img_logo, "Image 2"));
        list.add(new CardItemRecommendedCategoryModel(R.drawable.img_logo, "Image 3"));
        list.add(new CardItemRecommendedCategoryModel(R.drawable.img_logo, "Image 4"));
        list.add(new CardItemRecommendedCategoryModel(R.drawable.img_logo, "Image 5"));
        categoryAdapter.updateList(list);
    }
    //Recommended Product
    private void initRecommendedProduct(View v){
        rvProduct = v.findViewById(R.id.menuItemHomeRvRecommendedProduct);
        rvProduct.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
    }
    private void initAdapterRecommendedProduct(){
        productAdapter = new CardItemRecommendedProductAdapter();
        rvProduct.setAdapter(productAdapter);

    }
    private void loadDataRecommendedProduct(){
        List<CardItemRecommendedProductModel> list = new ArrayList<>();
        list.add(new CardItemRecommendedProductModel(R.drawable.img_logo, "Image 1","details 1"));
        list.add(new CardItemRecommendedProductModel(R.drawable.img_logo, "Image 2","details 2"));
        list.add(new CardItemRecommendedProductModel(R.drawable.img_logo, "Image 3","details 3"));
        list.add(new CardItemRecommendedProductModel(R.drawable.img_logo, "Image 4","details 4"));
        list.add(new CardItemRecommendedProductModel(R.drawable.img_logo, "Image 5","details 5"));
        list.add(new CardItemRecommendedProductModel(R.drawable.img_logo, "Image 6","details 6"));
        list.add(new CardItemRecommendedProductModel(R.drawable.img_logo, "Image 7","details 7"));
        productAdapter.updateList(list);
    }
}