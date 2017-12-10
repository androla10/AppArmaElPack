package com.armaelpack.armaelpack_envios;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Pedido;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback,LocationListener{

    private GoogleMap mMap;
    private LocationManager locationManager;
    private int LOCATION_REFRESH_TIME = 10000;
    private int LOCATION_REFRESH_DISTANCE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Pedido pedido = Control.getMiInstancia().miPedidoActual;


        LatLng ubicacion = new LatLng(Double.valueOf(pedido.getLatitud()),Double.valueOf(pedido.getLongitud()));
        mMap.addMarker(new MarkerOptions().position(ubicacion)).setTitle(pedido.getNomCliente());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,15));

        try {
            mMap.setMyLocationEnabled(true);
            obtenerUbicacion();
        }catch (SecurityException se){
            Toast.makeText(getApplicationContext(),"ERROR!! Activar ubicacion",Toast.LENGTH_SHORT).show();
        }


    }

    private void obtenerUbicacion(){
        try{
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,LOCATION_REFRESH_DISTANCE, (LocationListener) this);
        }catch (SecurityException e){
            Toast.makeText(getApplicationContext(),"Se porodujo un error al obtener "+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("GPS",location.getLatitude()+" - "+location.getLongitude());

        LatLng ubicacion = new LatLng(location.getLatitude(),location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,15));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }




    /**ESTOS DOS METODOS SON PARA PODER CONTROLAR EL MENU DE LA ESQUINA SUPERIOR DERECHA(LOGOUT)**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mapapedidos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_guardar) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**Se terminan los metodos**/
}
