package grupo6.proyectogrupo6.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.viewHolder.ProductViewHolder;

public class ProductRecycleViewAdapter extends ListAdapter<Product, ProductViewHolder> {

    public  ProductRecycleViewAdapter.OnItemClickListener listener;

    public ProductRecycleViewAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }
    public ProductRecycleViewAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback, ProductRecycleViewAdapter.OnItemClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bind(current);
    }

    public static class ProductDiff extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    }

    //---
    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

}
