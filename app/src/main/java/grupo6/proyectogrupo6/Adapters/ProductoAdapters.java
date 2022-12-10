package grupo6.proyectogrupo6.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import grupo6.proyectogrupo6.DB.DBHelper;
import grupo6.proyectogrupo6.Entities.Producto;
import grupo6.proyectogrupo6.Informacion;
import grupo6.proyectogrupo6.Productos;
import grupo6.proyectogrupo6.R;
import grupo6.proyectogrupo6.Services.ProductosServices;

public class ProductoAdapters extends BaseAdapter {

    private final Context context;
    private final ArrayList<Producto> arrayList;
    private ProductosServices productosServices;
    private DBHelper dbHelper;


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

    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        dbHelper = new DBHelper(context);
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.produtos_template, null);

        Producto producto = arrayList.get(position);

        TextView txtID = view.findViewById(R.id.txtID);
        ImageButton imgProductoTemplate = view.findViewById(R.id.imgProductoTemplate);
        TextView txtProductoTituloTemplate = view.findViewById(R.id.txtProductoTituloTemplate);
        TextView txtDescripcionTemplate = view.findViewById(R.id.txtDescripcionTemplate);
        TextView txtPrecioTemplate = view.findViewById(R.id.txtPrecioTemplate);
        Spinner spinnerMenu = view.findViewById(R.id.spinnerMenu);


        byte[] image = producto.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        txtID.setText(String.valueOf(producto.getId()));
        imgProductoTemplate.setImageBitmap(bitmap);
        txtProductoTituloTemplate.setText(producto.getNombre());
        txtDescripcionTemplate.setText(producto.getDescripcion());
        txtPrecioTemplate.setText("$" + producto.getPrecio());

        String[] opciones = {"Elija una opcion", "Actualizar", "Eliminar"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, opciones);
        spinnerMenu.setAdapter(adapter);


        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Eliminar")) {
                    int idP = Integer.parseInt(txtID.getText().toString().trim());
                    dbHelper.eliminarDatos(idP);
                    Intent intent = new Intent(context.getApplicationContext(), Productos.class);
                    context.startActivity(intent);

                } else if (parent.getItemAtPosition(position).equals("Actualizar")) {
                    Intent intent = new Intent(context.getApplicationContext(), AgregarProducto.class);
                    intent.putExtra("imageAtras", R.mipmap.atras);
                    intent.putExtra("imageTitulo", R.drawable.ferresix);
                    intent.putExtra("imageCarrito", R.drawable.carrito);

                    productosServices = new ProductosServices();
                    byte[] byteArray = productosServices.imageButtonToByte(imgProductoTemplate);

                    intent.putExtra("id", txtID.getText());
                    intent.putExtra("imageCode", byteArray);
                    intent.putExtra("titulo", txtProductoTituloTemplate.getText());
                    intent.putExtra("descripcion", txtDescripcionTemplate.getText());
                    intent.putExtra("precio", txtPrecioTemplate.getText());
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
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);

            productosServices = new ProductosServices();
            byte[] byteArray = productosServices.imageButtonToByte(imgProductoTemplate);

            intent.putExtra("ida", txtID.getText());
            intent.putExtra("imageCode", byteArray);
            intent.putExtra("titulo", txtProductoTituloTemplate.getText());
            intent.putExtra("descripcion", txtDescripcionTemplate.getText());
            intent.putExtra("precio", txtPrecioTemplate.getText());
            context.startActivity(intent);
        });

        txtProductoTituloTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Informacion.class);

            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);

            productosServices = new ProductosServices();
            byte[] byteArray = productosServices.imageButtonToByte(imgProductoTemplate);

            intent.putExtra("ida", txtID.getText());
            intent.putExtra("imageCode", byteArray);
            intent.putExtra("titulo", txtProductoTituloTemplate.getText());
            intent.putExtra("descripcion", txtDescripcionTemplate.getText());
            intent.putExtra("precio", txtPrecioTemplate.getText());
            context.startActivity(intent);
        });

        txtDescripcionTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Informacion.class);

            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);

            productosServices = new ProductosServices();
            byte[] byteArray = productosServices.imageButtonToByte(imgProductoTemplate);

            intent.putExtra("ida", txtID.getText());
            intent.putExtra("imageCode", byteArray);
            intent.putExtra("titulo", txtProductoTituloTemplate.getText());
            intent.putExtra("descripcion", txtDescripcionTemplate.getText());
            intent.putExtra("precio", txtPrecioTemplate.getText());
            context.startActivity(intent);
        });

        txtPrecioTemplate.setOnClickListener(View -> {
            Intent intent = new Intent(context.getApplicationContext(), Informacion.class);

            intent.putExtra("imageAtras", R.mipmap.atras);
            intent.putExtra("imageTitulo", R.drawable.ferresix);
            intent.putExtra("imageCarrito", R.drawable.carrito);

            productosServices = new ProductosServices();
            byte[] byteArray = productosServices.imageButtonToByte(imgProductoTemplate);

            intent.putExtra("ida", txtID.getText());
            intent.putExtra("imageCode", byteArray);
            intent.putExtra("titulo", txtProductoTituloTemplate.getText());
            intent.putExtra("descripcion", txtDescripcionTemplate.getText());
            intent.putExtra("precio", txtPrecioTemplate.getText());
            context.startActivity(intent);
        });
        return view;
    }


}
