package grupo6.proyectogrupo6.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public abstract class GenericEntityManagerAdapter<E> extends ListAdapter<E, GenericEntityManagerAdapter.ViewHolder> {

    public abstract int itemLayout();
    public abstract void bindItemView();
    public abstract void setItemView(E entity);

    public  GenericEntityManagerAdapter.OnItemClickListener listener;

    protected GenericEntityManagerAdapter(@NonNull DiffUtil.ItemCallback<E> diffCallback) {
        super(diffCallback);
    }
    protected GenericEntityManagerAdapter(GenericEntityManagerAdapter.OnItemClickListener listener) {
        super(new Diff<E>());
        this.listener = listener;
    }
    protected GenericEntityManagerAdapter(@NonNull AsyncDifferConfig<E> config) {
        super(config);
    }
    @Override @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent,this);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        E current = getItem(position);
        setItemView(current);
    }
    //---
    public interface OnItemClickListener<E> {
        void onItemClick(E entity);
    }
    //---
    protected static class Diff<E> extends DiffUtil.ItemCallback<E> {

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
    protected static class ViewHolder<E> extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView, @NonNull GenericEntityManagerAdapter adapter) {
            super(itemView);
            adapter.bindItemView();
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
