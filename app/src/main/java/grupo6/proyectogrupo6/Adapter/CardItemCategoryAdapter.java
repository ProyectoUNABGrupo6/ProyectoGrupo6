package grupo6.proyectogrupo6.Adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo6.proyectogrupo6.Model.CardItemCategoryModel;
import grupo6.proyectogrupo6.R;

public class CardItemCategoryAdapter extends RecyclerView.Adapter<CardItemCategoryAdapter.ViewHolder>{

    private  List<CardItemCategoryModel> list = new ArrayList<>();
    private  List<CardItemCategoryModel> listInitial = new ArrayList<>();
    private  CardItemCategoryAdapter.OnItemClickListener listener;

    public CardItemCategoryAdapter() {
    }

    public CardItemCategoryAdapter(List<CardItemCategoryModel> list) {
        this.listInitial = list;
        this.list = this.listInitial;
    }
    public CardItemCategoryAdapter(CardItemCategoryAdapter.OnItemClickListener listener) {
        this.listener = listener;
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
        this.listInitial = list;
        this.list = this.listInitial;
        notifyDataSetChanged();
    }

    public  void filter(String search){
        int length = search.length();

        if(length == 0){
            this.list = this.listInitial;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                this.list = listInitial.stream().
                        filter(item -> item.getName().toLowerCase().contains(search.toLowerCase()))
                        .collect(Collectors.toList());
            }else {
                list.clear();
                for (CardItemCategoryModel item : listInitial){
                    if(item.getName().toLowerCase().contains(search.toLowerCase())) list.add(item);
                }
            }
        }
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(cardItemCategoryModel);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CardItemCategoryModel cardItemCategoryModel);
    }
}
