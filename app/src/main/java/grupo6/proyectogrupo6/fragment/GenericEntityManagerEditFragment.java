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

import grupo6.proyectogrupo6.JsonUtil;
import grupo6.proyectogrupo6.Util;
import grupo6.proyectogrupo6.entity.GenericEntity;
import grupo6.proyectogrupo6.viewModel.GenericEntityManagerViewModel;

public abstract class GenericEntityManagerEditFragment<E extends GenericEntity,
                                                       V extends GenericEntityManagerViewModel>
                                                        extends Fragment implements View.OnClickListener{

    public abstract Class<E> getTypeClass();
    public abstract int getLayout();
    public abstract int getImageView();
    public abstract int getAddImageButton();
    public abstract int getSaveButton();
    public abstract int getDeleteButton();
    public abstract int getNavigationManagerFragment();
    public abstract void bindFields(View v);
    public abstract void initDataFields(View v,E data);
    public abstract String getErrorDataFields(E data);
    public abstract V constructViewModel();
    public abstract E getInstanceData();
    public abstract void updateData(E data);


    //add photo
    private ImageView image;
    private FloatingActionButton addImageButton;
    private ActivityResultLauncher<String> mGetContent;
    //Fields
    private E entity;
    private FloatingActionButton saveDataButton;
    private FloatingActionButton deleteDataButton;

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
        if(!isNewData(getDataBundle())) initDataFields(view,entity);
        initSaveData(view);
        initDeleteData(view);
    }
    @Override
    public void onClick(@NonNull View v) {
        int idButton = v.getId();
        if(idButton == getAddImageButton()) chooseFile();
        else if(idButton == getSaveButton()){
            updateData(this.entity);
            saveData(v,this.entity);
        }
        else if(idButton == getDeleteButton()) deleteData(v,this.entity);
    }
    //add photo
    protected void setImage(String stringUri){
        Util.setImageView(this.image,stringUri);
    }
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
    private void setUriImage(@NonNull Uri uri, Context c){
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
    private boolean isNewData(E data){
        return (data == null || data.getUid() == null || data.getUid() == 0);
    }
    public void saveData(View v,E data){
        String errorFields = getErrorDataFields(data);
        if(errorFields != null) {
            Toast toast = Toast.makeText(v.getContext(), errorFields, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        V viewModel = constructViewModel();
        if(isNewData(data)) viewModel.save(data);
        else viewModel.update(data);
        navigate(v,getNavigationManagerFragment());
    }
    private void initDeleteData(@NonNull View v){
        deleteDataButton = v.findViewById(getDeleteButton());
        if(isNewData(this.entity)) deleteDataButton.setVisibility(View.GONE);
        else deleteDataButton.setOnClickListener(this);
    }
    public void deleteData(View v,E data){
        V viewModel = constructViewModel();
        if(!isNewData(data))viewModel.delete(data);
        navigate(v,getNavigationManagerFragment());
    }
    //tool
    public E getDataBundle(){
        this.entity = null;
        if(getArguments() != null){
            String data = getArguments().getString(GenericEntityManagerFragment.keyDataBundle,null);
           this.entity = (data != null) ? JsonUtil.toObject(data,getTypeClass()):null;
        }
        if(this.entity == null) this.entity = getInstanceData();
        return this.entity;
    }
    @NonNull
    public String getStringField(Object field){
        if(field == null) return "";
        return field.toString();
    }
    public void navigate(View v, int idFragment){
        Navigation.findNavController(v).navigate(idFragment);
    }
}
