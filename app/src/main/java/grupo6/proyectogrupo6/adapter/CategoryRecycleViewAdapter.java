package grupo6.proyectogrupo6.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import grupo6.proyectogrupo6.entity.Category;
import grupo6.proyectogrupo6.R;

public class CategoryRecycleViewAdapter extends GenericEntityManagerAdapter<Category>{

    private ImageView img;
    private TextView name;
    private TextView description;

    public CategoryRecycleViewAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback) {
        super(diffCallback);
    }
    public CategoryRecycleViewAdapter(OnItemClickListener listener) {
        super(listener);
    }
    public CategoryRecycleViewAdapter(@NonNull AsyncDifferConfig<Category> config) {
        super(config);
    }

    @Override
    public int itemLayout() {
        return R.layout.category_item_layout;
    }

    @Override
    public void bindItemView(@NonNull View itemView) {
        this.img = itemView.findViewById(R.id.CategoryItemImage);
        this.name = itemView.findViewById(R.id.CategoryItemName);
        this.description = itemView.findViewById(R.id.CategoryItemDescription);
    }

    @Override
    public void setItemView(Category entity) {
        setImageView(img, entity.getUrlImage());
        name.setText(entity.getName());
        description.setText(entity.getDescription());
    }
}
