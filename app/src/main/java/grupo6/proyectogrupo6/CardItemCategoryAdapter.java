package grupo6.proyectogrupo6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardItemCategoryAdapter extends RecyclerView.Adapter<CardItemCategoryAdapter.ViewHolder>{

    private  List<CardItemCategoryModel> list = new ArrayList<>();

    public CardItemCategoryAdapter() {
    }

    public CardItemCategoryAdapter(List<CardItemCategoryModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_item_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updateItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<CardItemCategoryModel> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategory;
        TextView nameCategory;
        TextView descriptionCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           imgCategory = itemView.findViewById(R.id.cardItemCategoryImage);
           nameCategory= itemView.findViewById(R.id.cardItemCategoryName);
           descriptionCategory= itemView.findViewById(R.id.cardItemCategoryDescription);
        }

        public void updateItem(CardItemCategoryModel cardItemCategoryModel) {
            imgCategory.setImageResource(cardItemCategoryModel.getImage());
            nameCategory.setText(cardItemCategoryModel.getName());
            descriptionCategory.setText(cardItemCategoryModel.getDescription());
        }
    }
}
