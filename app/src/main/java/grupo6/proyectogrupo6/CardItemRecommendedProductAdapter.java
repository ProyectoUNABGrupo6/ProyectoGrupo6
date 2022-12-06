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

public class CardItemRecommendedProductAdapter extends RecyclerView.Adapter<CardItemRecommendedProductAdapter.ViewHolder>{

    private List<CardItemRecommendedProductModel> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_item_recommended_product,parent,false);
        return new CardItemRecommendedProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updateItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<CardItemRecommendedProductModel> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategory;
        TextView nameCategory;
        TextView detailsCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.cardItemRecommendedProductImage);
            nameCategory = itemView.findViewById(R.id.cardItemRecommendedProductName);
            detailsCategory = itemView.findViewById(R.id.cardItemRecommendedProductDetails);
        }

        public void updateItem(CardItemRecommendedProductModel cardItemRecommendedProductModel) {
            imgCategory.setImageResource(cardItemRecommendedProductModel.getImage());
            nameCategory.setText(cardItemRecommendedProductModel.getName());
            detailsCategory.setText(cardItemRecommendedProductModel.getDetails());
        }
    }

}
