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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.armaelpack.armaelpack_envios.adapters.ProductoAdapter;
import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Pedido;
import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetalleEnvio extends AppCompatActivity {

    /**VARIABLES PARA MANEJO DE MAPAS**/
    final int PERMISO_GPS=0;
    boolean tienePermiso = false;

    /**variables para el service**/
    private List<Producto> lstProducto = new ArrayList<>();
    private ProductoAdapter productoAdapter;
    private ListView lvProducto;


    private TextView tvNombreClienteFrag;
    private TextView tvCelularFragment;
    private TextView tvCorreoFragmentFragment;
    private TextView tvTotalNeto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_envio);

        /**mostrar el toolbar**/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tvNombreClienteFrag = findViewById(R.id.tvNombreClienteFrag);
        tvCelularFragment=findViewById(R.id.tvCelularFragment);
        tvCorreoFragmentFragment=findViewById(R.id.tvCorreoFragmentFragment);
        tvTotalNeto=findViewById(R.id.tvTotalNeto);

        Pedido pedido =Control.getMiInstancia().miPedidoActual;
        tvNombreClienteFrag.setText(pedido.getNomCliente());
        tvCelularFragment.setText(pedido.getCelular());
        tvCorreoFragmentFragment.setText(pedido.getCorreo());
        tvTotalNeto.setText(String.valueOf(pedido.getTotalNeto()));


        lvProducto=findViewById(R.id.lvDetalle);
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


        obtenerProducto();



    }



    public void obtenerProducto(){
        String urlProducto = "";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlProducto,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response.length()>0){
                                for (int i=0;i<response.length();i++){
                                    JSONObject jsonObject= response.getJSONObject(i);
                                    Producto producto = new Producto();
                                    producto.setIdPedido(jsonObject.getInt("idProducto"));
                                    producto.setCodigoProducto(jsonObject.getString("codigoProducto"));
                                    producto.setNombrePro(jsonObject.getString("nombre"));
                                    producto.setCantidadPro(jsonObject.getInt("cantidad"));
                                    producto.setTotalPro(jsonObject.getDouble("total"));


                                    lstProducto.add(producto);
                                }

                                productoAdapter = new ProductoAdapter(getApplicationContext(),lstProducto);
                                lvProducto.setAdapter(productoAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getNetworkTimeMs();
                    }
                }
        );
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
