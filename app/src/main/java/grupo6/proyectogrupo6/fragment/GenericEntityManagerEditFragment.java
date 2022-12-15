package grupo6.proyectogrupo6.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import grupo6.proyectogrupo6.RealPathUtil;
import grupo6.proyectogrupo6.viewModel.GenericEntityManagerViewModel;

public abstract class GenericEntityManagerEditFragment<E,
                                                       V extends GenericEntityManagerViewModel>
                                                        extends Fragment implements View.OnClickListener{

    public abstract int getLayout();
    public abstract int getImageView();
    public abstract int getAddImageButton();
    public abstract int getSaveButton();
    public abstract int getNavigationManagerFragment();
    public abstract void bindFields(View v);
    public abstract void initDataFields(View v);
    public abstract String getErrorDataFields(E data);
    public abstract V constructViewModel();
    public abstract E getDataFields();

    //add photo
    private FloatingActionButton addImageButton;
    private ImageView image;
    private ActivityResultLauncher<String> mGetContent;
    //Fields
    private FloatingActionButton saveDataButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //add photo button
        if(getImageView() != 0 && getAddImageButton() != 0) initSelectImage(view);
        bindFields(view);
        if(getArguments() != null) initDataFields(view);
        initSaveData(view);
    }
    @Override
    public void onClick(@NonNull View v) {
        if(v.getId() == getAddImageButton()) chooseFile();
        else if(v.getId() == getSaveButton()) saveData(v,getDataFields());
    }
    //add photo
    private void initSelectImage(@NonNull View v) {
        //button
        addImageButton = v.findViewById(getAddImageButton());
        addImageButton.setOnClickListener(this);
        // photo
        image = v.findViewById(getImageView());
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        Context c = getContext();
                        if(uri != null) setUriImage(uri,c);
                    }
                });
    }
    private void chooseFile() {
        mGetContent.launch("image/*");
    }
    private void setUriImage(Uri uri, Context c){
        //String path = RealPathUtil.getRealPath(c, uri);
        String path = uri.getPath();
        image.setTag(path);
        image.setImageURI(uri);
    }
    protected String getUriImage(){
        return getStringField(image.getTag());
    }
    //fields
    private void initSaveData(@NonNull View v){
        saveDataButton = v.findViewById(getSaveButton());
        saveDataButton.setOnClickListener(this);
    }
    public void saveData(View v,E data){
        String errorFields = getErrorDataFields(data);
        if(errorFields != null) {
            Toast toast = Toast.makeText(v.getContext(), errorFields, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        V viewModel = constructViewModel();
        viewModel.save(data);
        navigate(v,getNavigationManagerFragment());
    }
    //tool
    @NonNull
    public String getStringField(Object field){
        if(field == null) return "";
        return field.toString();
    }
    public void navigate(View v, int idFragment){
        Navigation.findNavController(v).navigate(idFragment);
    }
}
