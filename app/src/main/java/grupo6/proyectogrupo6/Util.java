package grupo6.proyectogrupo6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static <O extends Util.Filter> List<O> filter(List<O> list, @NonNull String search){
        int length = search.length();

        if(length == 0){
            return list;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return list.stream().
                        filter(item -> item.getIdentifier().toLowerCase().contains(search.toLowerCase()))
                        .collect(Collectors.toList());
            } else {
                List<O> outList = new ArrayList<O>();
                for (O item : list){
                    if(item.getIdentifier().toLowerCase().contains(search.toLowerCase())) outList.add(item);
                }
                return outList;
            }
        }
    }
    //Image
    public static void setImageView(ImageView imageView, int idRecurse){
        try {
            int recurse = (idRecurse != 0)?idRecurse:R.drawable.img_not_available;
            imageView.setImageResource(recurse);
        }catch (Exception e){
            imageView.setImageResource(R.drawable.img_not_available);
        }
    }
    public static void setImageView(ImageView imageView, String StringUri){
        try {
            Bitmap imgBitmap = null;
            if(StringUri != null && !StringUri.isEmpty()) {
                File imgFile = new File(StringUri);
                if (imgFile.exists()) imgBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            }
            if(imgBitmap != null) imageView.setImageBitmap(imgBitmap);
            else imageView.setImageResource(R.drawable.img_not_available);
        }catch (Exception e){
            imageView.setImageResource(R.drawable.img_not_available);
        }
    }

    public interface Filter {
        public String getIdentifier();
    }
}
