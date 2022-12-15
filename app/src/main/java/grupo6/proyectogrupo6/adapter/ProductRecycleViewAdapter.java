package grupo6.proyectogrupo6.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.viewHolder.ProductViewHolder;

public class ProductRecycleViewAdapter extends GenericEntityManagerAdapter<Product> {

    private ImageView img;
    private TextView name;
    private TextView description;
    private TextView price;

    public ProductRecycleViewAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }
    public ProductRecycleViewAdapter(OnItemClickListener listener) {
        super(listener);
    }
    public ProductRecycleViewAdapter(@NonNull AsyncDifferConfig<Product> config) {
        super(config);
    }
    @Override
    public int itemLayout() {
        return R.layout.product_item_layout;
    }
    @Override
    public void bindItemView(View itemView) {
        this.img = itemView.findViewById(R.id.ProductItemImage);
        this.name = itemView.findViewById(R.id.ProductItemName);
        this.description = itemView.findViewById(R.id.ProductItemDescription);
        this.price = itemView.findViewById(R.id.ProductItemPrice);
    }
    @Override
    public void setItemView(@NonNull Product entity) {
        setImageView(img, entity.getUrlImage());
        name.setText(entity.getName());
        description.setText(entity.getDescription());
        price.setText(entity.getPrice());
    }
}
