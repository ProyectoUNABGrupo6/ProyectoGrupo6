package grupo6.proyectogrupo6.Adapters;

import android.annotation.SuppressLint;
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

import grupo6.proyectogrupo6.AgregarProducto;
import grupo6.proyectogrupo6.DB.DBFirebase;
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Entities.Usuario;
import grupo6.proyectogrupo6.Informacion;
import grupo6.proyectogrupo6.Productos;
import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class ProductoAdapters extends BaseAdapter {

    private Context context;
    private ArrayList<Producto> arrayList;
    private ArrayList<Usuario> arrayUsuario;


    ProductosServices productosServices;
    private DBHelper dbHelper;
    private DBFirebase dbFirebase;
    private Productos productos;


    public ProductoAdapters(Context context, ArrayList<Producto> arrayList, ArrayList<Usuario> arrayUsuario) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayUsuario = arrayUsuario;
        this.productosServices = new ProductosServices();
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

    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        dbHelper = new DBHelper(context);
        dbFirebase = new DBFirebase();
        productos = new Productos();
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.produtos_template, null);

        Producto producto = arrayList.get(position);


        TextView txtID = view.findViewById(R.id.txtID);
        ImageButton imgProductoTemplate = view.findViewById(R.id.imgProductoTemplate);
        TextView txtProductoTituloTemplate = view.findViewById(R.id.txtProductoTituloTemplate);
        TextView txtDescripcionTemplate = view.findViewById(R.id.txtDescripcionTemplate);
        TextView txtPrecioTemplate = view.findViewById(R.id.txtPrecioTemplate);
        TextView txtUsuP = view.findViewById(R.id.txtUsuP);
        TextView txtCategoriaT = view.findViewById(R.id.txtCategoriaTemplate);
        Spinner spinnerMenu = view.findViewById(R.id.spinnerMenu);


        txtID.setText(String.valueOf(producto.getId()));
        productosServices.insertarImagen(producto.getImagen(), imgProductoTemplate, context);
        txtProductoTituloTemplate.setText(producto.getNombre());
        txtDescripcionTemplate.setText(producto.getDescripcion());
        txtCategoriaT.setText(producto.getCategoria());
        txtPrecioTemplate.setText("$" + producto.getPrecio());


        if (arrayUsuario.size() != 0) {

            int posicion = 0;
            Usuario usuario = arrayUsuario.get(posicion);
            String user = usuario.getEmail();

            txtUsuP.setText(user);
        }

        if (!txtUsuP.getText().toString().isEmpty()) {
            spinnerMenu.setVisibility(View.VISIBLE);
        }


        String[] opciones = {"Elija una opcion", "Actualizar", "Eliminar"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, opciones);
        spinnerMenu.setAdapter(adapter);


        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Eliminar")) {
                    String idP = txtID.getText().toString().trim();
                    dbHelper.eliminarDatos(idP);
                    dbFirebase.eliminarDatos(idP);
                    Intent intent = new Intent(context.getApplicationContext(), Productos.class);
                    intent.putExtra("categoria", producto.getCategoria());
                    context.startActivity(intent);

                } else if (parent.getItemAtPosition(position).equals("Actualizar")) {
                    Intent intent = new Intent(context.getApplicationContext(), AgregarProducto.class);

                    intent.putExtra("usuario", txtUsuP.getText());

                    intent.putExtra("id", txtID.getText());

                    intent.putExtra("titulo", txtProductoTituloTemplate.getText());
                    intent.putExtra("descripcion", txtDescripcionTemplate.getText());
                    intent.putExtra("categoria", txtCategoriaT.getText());
                    intent.putExtra("precio", txtPrecioTemplate.getText());
                    intent.putExtra("imagen", producto.getImagen());
                    context.startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgProductoTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Informacion.class);


            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageCarrito", R.drawable.carrito);


            intent.putExtra("ida", producto.getId());
            intent.putExtra("titulo", producto.getNombre());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("categoria", producto.getCategoria());
            intent.putExtra("imagen", String.valueOf(producto.getImagen()));
            intent.putExtra("precio", String.valueOf(producto.getPrecio()));
            context.startActivity(intent);
        });

        txtProductoTituloTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Informacion.class);


            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageCarrito", R.drawable.carrito);


            intent.putExtra("ida", producto.getId());
            intent.putExtra("titulo", producto.getNombre());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("categoria", producto.getCategoria());
            intent.putExtra("imagen", String.valueOf(producto.getImagen()));
            intent.putExtra("precio", String.valueOf(producto.getPrecio()));
            context.startActivity(intent);
        });

        txtDescripcionTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Informacion.class);


            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageCarrito", R.drawable.carrito);


            intent.putExtra("ida", producto.getId());
            intent.putExtra("titulo", producto.getNombre());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("categoria", producto.getCategoria());
            intent.putExtra("imagen", String.valueOf(producto.getImagen()));
            intent.putExtra("precio", String.valueOf(producto.getPrecio()));
            context.startActivity(intent);
        });

        txtPrecioTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Informacion.class);


            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageCarrito", R.drawable.carrito);


            intent.putExtra("ida", producto.getId());
            intent.putExtra("titulo", producto.getNombre());
            intent.putExtra("descripcion", producto.getDescripcion());
            intent.putExtra("categoria", producto.getCategoria());
            intent.putExtra("imagen", String.valueOf(producto.getImagen()));
            intent.putExtra("precio", String.valueOf(producto.getPrecio()));
            context.startActivity(intent);
        });
        return view;
    }


}
