package grupo6.proyectogrupo6;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class MenuItemCategoryAddFragment extends Fragment
                                         implements View.OnClickListener{

    //add photo
    private FloatingActionButton addPhotoButton;
    private ImageView addImg;
    private ActivityResultLauncher<String> mGetContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_item_category_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //add photo button
        initSelectPhoto(view);
    }


    //add photo button
    private void initSelectPhoto(View v) {

        //button
        addPhotoButton = v.findViewById(R.id.menuItemCategoryAddImgButton);
        addPhotoButton.setOnClickListener(this);
        // photo
        addImg = v.findViewById(R.id.menuItemCategoryAddImg);
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        if(uri != null) addImg.setImageURI(uri);
                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.menuItemCategoryAddImgButton:
                chooseFile();
                break;
            case R.id.menuItemCategoryOkButton:
                break;
            default:
                break;
        }

    }

    private void chooseFile() {
        mGetContent.launch("image/*");
    }

}

