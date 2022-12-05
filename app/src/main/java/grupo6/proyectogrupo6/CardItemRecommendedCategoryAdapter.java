package grupo6.proyectogrupo6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CardItemRecommendedCategoryAdapter extends ArrayAdapter<CardItemRecommendedCategoryModel> {

    public CardItemRecommendedCategoryAdapter(@NonNull Context context, int resource, @NonNull List<CardItemRecommendedCategoryModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_card_item_recommended_category, parent, false);
        }
        CardItemRecommendedCategoryModel c = this.getItem(position);
        ImageView imgCategoria = convertView.findViewById(R.id.cardItemRecommendedCategoryImage);
        TextView titleCategoria= convertView.findViewById(R.id.cardItemRecommendedCategoryName);

        imgCategoria.setImageResource(c.getImagen());
        titleCategoria.setText(c.getTitulo());

        return convertView;
    }

    public void updateItem(List<CardItemRecommendedCategoryModel> lista) {
        clear();
        addAll(lista);
        this.notifyDataSetChanged();
    }
}
