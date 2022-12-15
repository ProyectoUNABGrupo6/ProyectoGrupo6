package grupo6.proyectogrupo6;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;

public class Maps extends AppCompatActivity {

    private MapView map;
    private MapController mapController;

    public ImageButton botonAtrasForm;
    public ImageView imgTituloForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        botonAtrasForm = findViewById(R.id.imgAtrasForm);
        imgTituloForm = findViewById(R.id.imgTituloForm);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int imgTit = bundle.getInt("imageTitulo");
            int imgAtras = bundle.getInt("imageAtras");
            botonAtrasForm.setImageResource(imgAtras);
            imgTituloForm.setImageResource(imgTit);

        }


        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map = (MapView) findViewById(R.id.map);
        map.setBuiltInZoomControls(true);
        mapController = (MapController) map.getController();
        GeoPoint colombia = new GeoPoint(4.570868, -74.297333);
        mapController.setCenter(colombia);
        mapController.setZoom(8);
        map.setMultiTouchControls(true);
        MapEventsReceiver mapEventsReceiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                Toast.makeText(getApplicationContext(),
                        "Latitud: " + p.getLatitude()
                                + "  " +
                                "Longitud: " + p.getLongitude(), Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, mapEventsReceiver);
        map.getOverlays().add(mapEventsOverlay);

        botonAtrasForm.setOnClickListener(this::volverAtras);
    }

    public void volverAtras(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("imageTitulo", R.drawable.ferresix);
        intent.putExtra("imageCarrito", R.drawable.carrito);
        startActivity(intent);
    }

}
