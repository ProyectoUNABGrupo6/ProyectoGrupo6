package grupo6.proyectogrupo6;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import grupo6.proyectogrupo6.model.SliderItemImageModel;

public class MenuItemHomeSliderAdapter extends SliderViewAdapter<MenuItemHomeSliderAdapter.SliderAdapterVH> {

    private Context context;
    List<SliderItemImageModel> mSliderItems = new ArrayList<>();

    public MenuItemHomeSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_image_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        SliderItemImageModel sliderItem = mSliderItems.get(position);

        viewHolder.textView.setText(sliderItem.getTitulo());
        viewHolder.textView.setTextSize(16);
        viewHolder.textView.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImagen())
                .fitCenter()
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    protected class SliderAdapterVH extends SliderViewAdapter.ViewHolder{
        View itemView;
        ImageView imageView;
        TextView textView;

        public SliderAdapterVH(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.slider_item_image_auto_iv);
            textView = itemView.findViewById(R.id.slider_item_image_auto_tv);
            this.itemView = itemView;
        }
    }

    public void updateItem(List<SliderItemImageModel> lista) {
        mSliderItems.clear();
        mSliderItems.addAll(lista);
        this.notifyDataSetChanged();
    }

}
