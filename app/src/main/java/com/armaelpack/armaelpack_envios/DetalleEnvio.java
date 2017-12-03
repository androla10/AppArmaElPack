package com.armaelpack.armaelpack_envios;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DetalleEnvio extends AppCompatActivity {

    /**VARIABLES PARA MANEJO DE MAPAS**/
    final int PERMISO_GPS=0;
    boolean tienePermiso = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_envio);

        /**mostrar el toolbar**/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /**PARA QUE FUNCIONE EL BOTON E VER EL MAPA**/
        Button btnVerDireccion = findViewById(R.id.btnVerDireccion);
        btnVerDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tienePermiso=true){
                Intent intent = new Intent(DetalleEnvio.this,Mapa.class);
                startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"No tiene permiso para el GPS",Toast.LENGTH_SHORT).show();
                }
            }
        });


        /**PARA OBTENER LA VERSION DEL ANDROID**/
        int currentApiVersion= android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.M){
            validarUSOUbicacion();
        }else {
            tienePermiso=true;
        }





    }






    /**ACA SE CARGARA EL MENU PARA ESTE ACTIVITY**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_guardar_pedido) {
            Toast.makeText(getApplicationContext(),"Se guardo el detalle",Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /********************************************************************************************************************************/






    /*****VALIDACIONES Y METODOS PARA VER EL MAPA**/

    @TargetApi(Build.VERSION_CODES.M)
    private void validarUSOUbicacion(){
        final int iGPS=checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (iGPS != PackageManager.PERMISSION_GRANTED){
            final boolean pGPS=shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
            if (pGPS){

            }else {

            }
            String[] permiso=new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permiso,PERMISO_GPS);
        }else {
            tienePermiso=true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISO_GPS:
                if (grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    tienePermiso = true;
                }else {
                    Toast.makeText(getApplicationContext(),"No podra hacer uso del GPS!!!",Toast.LENGTH_SHORT).show();
                    tienePermiso=false;
                }
        }
    }
    /********************************************************************************************************************************/
}
