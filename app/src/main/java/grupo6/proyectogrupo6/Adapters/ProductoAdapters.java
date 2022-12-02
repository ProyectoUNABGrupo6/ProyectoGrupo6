package grupo6.proyectogrupo6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Informacion;
import grupo6.proyectogrupo6.Productos;
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

        imgProductoTemplate.setImageResource(producto.getImagen());
        txtProductoTituloTemplate.setText(producto.getName());
        txtDescripcionTemplate.setText(producto.getDescripcion());
        txtPrecioTemplate.setText(String.valueOf(producto.getPrecio()));

        imgProductoTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context, Informacion.class);

            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);
            intent.putExtra("titulo", producto.getName());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("imageCode", producto.getImagen());
            intent.putExtra("precio", producto.getPrecio());
            context.startActivity(intent);
        });

        return view;
    }

}
