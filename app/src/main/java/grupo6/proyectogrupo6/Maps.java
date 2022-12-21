package grupo6.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import java.text.CollationElementIterator;

public class Maps extends AppCompatActivity {
    private FloatingActionButton btnSearch;
    private MapView map;
    private MapController mapController;
    private LocationManager locationManager;
    private Location currentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));





        map = (MapView) findViewById(R.id.map);

        map.setBuiltInZoomControls(true);
        mapController = (MapController)map.getController();
        //GeoPoint colombia = new GeoPoint(4.747, -74.032);
        //mapController.setCenter(colombia);
        //mapController.setZoom(6);

        Intent intent = getIntent();
        GeoPoint punto = new GeoPoint(intent.getDoubleExtra("Latitud", 4.747), intent.getDoubleExtra("Longitud", -74.032));
        mapController.setCenter(punto);
        mapController.setZoom(10);
        map.setMultiTouchControls(true);


        MapEventsReceiver mapEventsReceiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                //Toast.makeText(getApplicationContext(),
                // "Latitud: " + p.getLatitude()
                //        + " " +
                //"Longitud: " + p.getLongitude(), Toast.LENGTH_SHORT).show();
                Marker marker = new Marker(map);
                marker.setPosition(punto);
                map.getOverlays().add(marker);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                //map.getOverlays().add(marker);


                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };


        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, mapEventsReceiver);
        map.getOverlays().add(mapEventsOverlay);

        btnSearch = (FloatingActionButton) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Sucursales.class);
                startActivity(intent);
            }
        });



    }
}