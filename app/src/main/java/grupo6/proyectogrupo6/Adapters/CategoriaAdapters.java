package grupo6.proyectogrupo6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Categoria;
import grupo6.proyectogrupo6.MainActivity;
import grupo6.proyectogrupo6.Productos;
import grupo6.proyectogrupo6.R;

public class CategoriaAdapters extends BaseAdapter {

    private final Context context;
    private final ArrayList<Categoria> arrayCategoria;

    public DBHelper dbHelper;
    public DBFirebase dbFirebase;
    public MainActivity mainActivity;

    public CategoriaAdapters(Context context, ArrayList<Categoria> arrayCategoria) {
        this.context = context;
        this.arrayCategoria = arrayCategoria;
    }

    @Override
    public int getCount() {
        return arrayCategoria.size();
    }

    @Override
    public Object getItem(int p) {
        return arrayCategoria.get(p);
    }

    @Override
    public long getItemId(int p) {
        return p;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        dbHelper = new DBHelper(context);
        dbFirebase = new DBFirebase();
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.categorias_template, null);
        Categoria categoria = arrayCategoria.get(position);


        ImageButton imgCat = view.findViewById(R.id.imgCategoria);
        TextView txtCategoria = view.findViewById(R.id.txtCategoria);



        String cat = categoria.getCategoria();
        txtCategoria.setText(cat);


        txtCategoria.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Productos.class);


            intent.putExtra("categoria", txtCategoria.getText());
            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);
            context.startActivity(intent);
        });


        return view;
    }
}
