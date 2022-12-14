package grupo6.proyectogrupo6.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.adapter.ProductRecycleViewAdapter;
import grupo6.proyectogrupo6.entity.Product;

public class ProductViewHolder extends RecyclerView.ViewHolder{

    private final ImageView img;
    private final TextView name;
    private final TextView description;
    private final TextView price;
    private final ProductRecycleViewAdapter adapter;

    public ProductViewHolder(@NonNull View itemView, ProductRecycleViewAdapter adapter) {
        super(itemView);
        this.img = itemView.findViewById(R.id.ProductItemImage);
        this.name = itemView.findViewById(R.id.ProductItemName);
        this.description = itemView.findViewById(R.id.ProductItemDescription);
        this.price = itemView.findViewById(R.id.ProductItemPrice);
        this.adapter = adapter;
    }

    public void bind(Product data) {
        img.setImageResource(R.drawable.img_logo);
        name.setText(data.getName());
        description.setText(data.getDescription());
        price.setText(data.getPrice());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.listener.onItemClick(data);
            }
        });
    }

    public static ProductViewHolder create(ViewGroup parent, ProductRecycleViewAdapter adapter) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_layout, parent, false);
        return new ProductViewHolder(view,adapter);
    }
}
