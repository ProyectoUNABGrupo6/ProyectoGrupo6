package grupo6.proyectogrupo6.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import grupo6.proyectogrupo6.model.HomeCategoryRecommendedItemRecycleViewModel;
import grupo6.proyectogrupo6.R;

public class HomeCategoryRecommendedRecycleViewAdapter extends RecyclerView.Adapter<HomeCategoryRecommendedRecycleViewAdapter.ViewHolder>{

    private  List<HomeCategoryRecommendedItemRecycleViewModel> list = new ArrayList<>();

    public HomeCategoryRecommendedRecycleViewAdapter() {
    }

    public HomeCategoryRecommendedRecycleViewAdapter(List<HomeCategoryRecommendedItemRecycleViewModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_recommended_item_layout,parent,false);
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

    public void updateList(List<HomeCategoryRecommendedItemRecycleViewModel> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategory;
        TextView titleCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           imgCategory = itemView.findViewById(R.id.cardItemRecommendedCategoryImage);
           titleCategory= itemView.findViewById(R.id.cardItemRecommendedCategoryName);
        }

        public void updateItem(HomeCategoryRecommendedItemRecycleViewModel cardItemRecommendedCategoryModel) {
            imgCategory.setImageResource(cardItemRecommendedCategoryModel.getImagen());
            titleCategory.setText(cardItemRecommendedCategoryModel.getTitulo());
        }
    }
}
