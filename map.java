package com.example.user.safetransitproject;

import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.maps.model.GeocodedWaypoint;

public class map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng latlng;
    private static final LatLng KIT = new LatLng(28.698980, 77.155147);
    private static final LatLng GTB = new LatLng(28.700932, 77.208888);
    private static final LatLng ny= new LatLng(40.713468, 73.978243);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Polyline polyline = googleMap.addPolyline(new PolylineOptions().clickable(true).add(
           new LatLng(35.016,143.321),
           new LatLng(34.747,145.592),
           new LatLng(34.364,147.891),
           new LatLng(33.501,150.217),
           new LatLng(32.306,149.248),
           new LatLng(32.491,147.309)));
        // Add a marker in Sydney and move the camera
        LatLng KIT = new LatLng(-28.698980, 77.155147);
        mMap.addMarker(new MarkerOptions().position(KIT).title("Marker in Kasturba"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(KIT));
        LatLng GTB = new LatLng(28.700932, 77.208888);
        mMap.addMarker(new MarkerOptions().position(GTB).title("Marker in GTB nagar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(GTB));
        LatLng ny = new LatLng(40.713468, 73.978243);
        mMap.addMarker(new MarkerOptions().position(ny).title("New york"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }

    public void processMap(View view){
        if(mMap!=null){
            PolylineOptions polyline = new PolylineOptions().add(KIT).add(GTB).add(ny).width(5).geodesic(true);
            mMap.addPolyline(polyline);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KIT,13));
        }
    }


    }

