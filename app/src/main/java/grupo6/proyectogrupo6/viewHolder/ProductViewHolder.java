package grupo6.proyectogrupo6.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.entity.Product;

public class ProductViewHolder extends RecyclerView.ViewHolder{

    private final ImageView img;
    private final TextView name;
    private final TextView description;
    private final TextView price;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.cardItemProductImage);
        name = itemView.findViewById(R.id.cardItemProductName);
        description = itemView.findViewById(R.id.cardItemProductDescription);
        price = itemView.findViewById(R.id.cardItemProductPrice);
    }

    public void bind(Product data) {
        img.setImageResource(R.drawable.img_logo);
        name.setText(data.getName());
        description.setText(data.getDescription());
        price.setText(data.getPrice());
    }

    public static ProductViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_item_product, parent, false);
        return new ProductViewHolder(view);
    }
}
