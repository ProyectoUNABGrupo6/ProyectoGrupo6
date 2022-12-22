package grupo6.proyectogrupo6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import grupo6.proyectogrupo6.AgregarCategoria;
import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Categoria;
import grupo6.proyectogrupo6.Entities.Usuario;
import grupo6.proyectogrupo6.MainActivity;
import grupo6.proyectogrupo6.Productos;
import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class CategoriaAdapters extends BaseAdapter {

    private final Context context;
    private final ArrayList<Categoria> arrayCategoria;
    private ArrayList<Usuario> arrayUsuario;

    public DBHelper dbHelper;
    public DBFirebase dbFirebase;
    public MainActivity mainActivity;
    ProductosServices productosServices;

    public CategoriaAdapters(Context context, ArrayList<Categoria> arrayCategoria, ArrayList<Usuario> arrayUsuario) {
        this.context = context;
        this.arrayCategoria = arrayCategoria;
        this.arrayUsuario = arrayUsuario;
        this.productosServices = new ProductosServices();
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
        TextView txtUsuCat = view.findViewById(R.id.txtUsuC);
        TextView txtID = view.findViewById(R.id.txtIDCat);
        Spinner spinnercat = view.findViewById(R.id.spinnerCat);

        txtCategoria.setText(categoria.getCategoria());
        txtID.setText(categoria.getIdCat());
        productosServices.insertarImagen(categoria.getImagen(), imgCat, context);

        if (arrayUsuario.size() != 0) {

            int posicion = 0;
            Usuario usuario = arrayUsuario.get(posicion);
            String user = usuario.getEmail();

            txtUsuCat.setText(user);
        }

        if (!txtUsuCat.getText().toString().isEmpty()) {
            spinnercat.setVisibility(View.VISIBLE);
        }

        String[] opciones = {"Elija una opcion", "Actualizar", "Eliminar"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, opciones);
        spinnercat.setAdapter(adapter);


        spinnercat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Eliminar")) {
                    String idC = txtID.getText().toString().trim();
                    dbHelper.eliminarCategoria(idC);
                    dbFirebase.eliminarCategoria(idC);
                    Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                    context.startActivity(intent);

                } else if (parent.getItemAtPosition(position).equals("Actualizar")) {
                    Intent intent = new Intent(context.getApplicationContext(), AgregarCategoria.class);
                    intent.putExtra("usuario", txtUsuCat.getText());
                    intent.putExtra("id", txtID.getText());
                    intent.putExtra("categoria", txtCategoria.getText());

                    context.startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        txtCategoria.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Productos.class);

            intent.putExtra("categoria", txtCategoria.getText());
            context.startActivity(intent);
        });


        return view;
    }
}
