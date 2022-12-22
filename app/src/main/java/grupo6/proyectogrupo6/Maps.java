package grupo6.proyectogrupo6;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class Maps extends AppCompatActivity {
    public FloatingActionButton btnSearch, btnAtras;
    private MapView map;
    public MapController mapController;
    public TextView user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        btnAtras = findViewById(R.id.imgAtrasMap);
        btnSearch = findViewById(R.id.btnSearch);
        user = findViewById(R.id.txtUsuMap);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String users = bundle.getString("usuario");
            user.setText(users);
        }

        btnAtras.setOnClickListener(View -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        map = (MapView) findViewById(R.id.map);

        map.setBuiltInZoomControls(true);
        mapController = (MapController) map.getController();


        Intent intent = getIntent();
        GeoPoint punto = new GeoPoint(intent.getDoubleExtra("Latitud", 4.747), intent.getDoubleExtra("Longitud", -74.032));
        mapController.setCenter(punto);
        mapController.setZoom(10);
        map.setMultiTouchControls(true);


        MapEventsReceiver mapEventsReceiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                Marker marker = new Marker(map);
                marker.setPosition(punto);
                map.getOverlays().add(marker);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);



                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };


        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, mapEventsReceiver);
        map.getOverlays().add(mapEventsOverlay);

        btnSearch.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), Sucursales.class);
            startActivity(intent1);
        });


    }
}