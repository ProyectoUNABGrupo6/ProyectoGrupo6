package grupo6.proyectogrupo6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Informacion;
import grupo6.proyectogrupo6.R;

public class ProductoAdapters extends BaseAdapter {

    private Context context;
    private ArrayList<Producto> arrayList;

    public ProductoAdapters(Context context, ArrayList<Producto> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.produtos_template, null);

        Producto producto = arrayList.get(position);

        ImageButton imgProductoTemplate = view.findViewById(R.id.imgProductoTemplate);
        TextView txtProductoTituloTemplate = view.findViewById(R.id.txtProductoTituloTemplate);
        TextView txtDescripcionTemplate = view.findViewById(R.id.txtDescripcionTemplate);
        TextView txtPrecioTemplate = view.findViewById(R.id.txtPrecioTemplate);


        byte[] image = producto.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imgProductoTemplate.setImageBitmap(bitmap);
        txtProductoTituloTemplate.setText(producto.getNombre());
        txtDescripcionTemplate.setText(producto.getDescripcion());
        txtPrecioTemplate.setText("$" + producto.getPrecio());

        imgProductoTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Informacion.class);

            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            intent.putExtra("imageCode", byteArray);
            intent.putExtra("titulo", producto.getNombre());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("precio", producto.getPrecio());
            context.startActivity(intent);
        });

        txtProductoTituloTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context, Informacion.class);

            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);


            intent.putExtra("titulo", producto.getNombre());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("imageCode", producto.getImagen());
            intent.putExtra("precio", producto.getPrecio());
            context.startActivity(intent);
        });

        txtDescripcionTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context, Informacion.class);

            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);


            intent.putExtra("titulo", producto.getNombre());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("imageCode", producto.getImagen());
            intent.putExtra("precio", producto.getPrecio());
            context.startActivity(intent);
        });

        txtPrecioTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context, Informacion.class);

            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);


            intent.putExtra("titulo", producto.getNombre());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("imageCode", producto.getImagen());
            intent.putExtra("precio", producto.getPrecio());
            context.startActivity(intent);
        });


        return view;
    }


}
