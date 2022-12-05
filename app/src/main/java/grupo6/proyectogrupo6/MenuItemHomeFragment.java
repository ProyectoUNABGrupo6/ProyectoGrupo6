package grupo6.proyectogrupo6;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MenuItemHomeFragment extends Fragment {

    private SliderView svm;
    private MenuItemHomeSliderAdapter misa;

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
        init(view);
        initAdapter();
        loadData();
    }

    private void init(View v){
        svm = v.findViewById(R.id.menuItemHomeSliderImage);
    }
    private void initAdapter() {
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
    private void loadData() {

        List<SliderItemImageModel> lista = new ArrayList<>();
        lista.add(new SliderItemImageModel(R.drawable.img_logo, "Imagen 1"));
        lista.add(new SliderItemImageModel(R.drawable.img_logo, "Imagen 2"));
        lista.add(new SliderItemImageModel(R.drawable.img_logo, "Imagen 3"));
        lista.add(new SliderItemImageModel(R.drawable.img_logo, "Imagen 4"));
        misa.updateItem(lista);
    }
}