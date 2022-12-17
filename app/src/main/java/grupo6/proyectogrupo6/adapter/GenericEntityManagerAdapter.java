package grupo6.proyectogrupo6.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.Util;

public abstract class GenericEntityManagerAdapter<E> extends ListAdapter<E, GenericEntityManagerAdapter.ViewHolder> {

    public abstract int itemLayout();
    public abstract void bindItemView(View itemView);
    public abstract void setItemView(E entity);

    public GenericEntityManagerAdapter.OnItemClickListener listener;

    public GenericEntityManagerAdapter(@NonNull DiffUtil.ItemCallback<E> diffCallback) {
        super(diffCallback);
    }
    public GenericEntityManagerAdapter(GenericEntityManagerAdapter.OnItemClickListener listener) {
        super(new Diff<E>());
        this.listener = listener;
    }
    public GenericEntityManagerAdapter(@NonNull AsyncDifferConfig<E> config) {
        super(config);
    }
    @Override @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent,this);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        E current = getItem(position);
        holder.setItemView(current,this);
    }
    public void setImageView(ImageView imageView,String stringUri){
        Util.setImageView(imageView,stringUri);
    }
    //---
    public interface OnItemClickListener<E> {
        void onItemClick(E entity);
    }
    //---
    public static class Diff<E> extends DiffUtil.ItemCallback<E> {

        @Override
        public boolean areItemsTheSame(@NonNull E oldItem, @NonNull E newItem) {
            return oldItem == newItem;
        }
        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull E oldItem, @NonNull E newItem) {
            return oldItem.equals(newItem);
        }
    }
    public static class ViewHolder<E> extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView, @NonNull GenericEntityManagerAdapter adapter) {
            super(itemView);
            adapter.bindItemView(itemView);
        }
        @NonNull
        public static ViewHolder create(@NonNull ViewGroup parent, @NonNull GenericEntityManagerAdapter adapter) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(adapter.itemLayout(), parent, false);
            return new ViewHolder(view,adapter);
        }
        public void setItemView(E entity, @NonNull GenericEntityManagerAdapter adapter) {
            adapter.setItemView(entity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.listener.onItemClick(entity);
                }
            });
        }
    }

}
