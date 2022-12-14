package grupo6.proyectogrupo6.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;


import java.util.List;

import grupo6.proyectogrupo6.adapter.GenericEntityManagerAdapter;
import grupo6.proyectogrupo6.viewModel.GenericEntityManagerViewModel;

public abstract class GenericEntityManagerFragment<E,
                                                   A extends GenericEntityManagerAdapter,
                                                   V extends GenericEntityManagerViewModel>
                                                   extends Fragment
                                                   implements SearchView.OnQueryTextListener,
                                                              View.OnClickListener {


    public abstract int getLayout();
    public abstract int getItemLayout();
    public abstract int getSearchView();
    public abstract int getManagerItemLayout();
    public abstract int getAddItemButton();
    public abstract void buildBundle(Bundle bundle,E entity);
    public abstract A getAdapter(GenericEntityManagerAdapter.OnItemClickListener<E> onItemClickListener);
    public abstract V constructViewModel();

    private RecyclerView rv;
    private SearchView sv;
    private A adapter;
    private V viewModel;
    private FloatingActionButton addButton;

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
        initAdapter(view);
        initRecyclerView(view);
        initModelView(view);
        initSearchView(view);
        initAddButton(view);
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        applySearchView(s);
        return false;
    }
    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
    @Override
    public void onClick(@NonNull View v) {
        if(v.getId() == getAddItemButton()){
            navigate(v,getManagerItemLayout(),null);
        }
    }
    public void initAdapter(View v){
        adapter = getAdapter(new GenericEntityManagerAdapter.OnItemClickListener<E>() {
            @Override
            public void onItemClick(E entity) {
                navigate(v,getManagerItemLayout(),entity);
            }
        });
    }
    public void initRecyclerView(@NonNull View v){
        rv = v.findViewById(getItemLayout());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
    }
    public void initModelView(View v){
        this.viewModel = constructViewModel();
        updateModelView(getViewModel().getEntities());
    }
    public V getViewModel(){
        return viewModel;
    }
    public void updateModelView(@NonNull LiveData<List<E>> newList){
        newList.observe(getViewLifecycleOwner(), list -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList((List) list);
        });
    }
    public void initSearchView(@NonNull View v){
        sv = v.findViewById(getSearchView());
        sv.setOnQueryTextListener(this);
    }
    public void applySearchView(String search){
        updateModelView(getViewModel().getEntitiesByIdentifierLike(search));
    }
    public void initAddButton(@NonNull View v){
        addButton = v.findViewById(getAddItemButton());
        addButton.setOnClickListener(this);
    }
    public void navigate(View v, int idFragment, E entity){
        Bundle bundle = new Bundle();
        if(entity != null) {
            buildBundle(bundle,entity);
        }
        Navigation.findNavController(v).navigate(idFragment,bundle);
    }
}
