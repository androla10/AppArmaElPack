package com.armaelpack.armaelpack_envios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Usuario;

import org.json.JSONObject;

import static com.android.volley.Response.*;

public class Logueo extends AppCompatActivity {

/*
    //para que funcione el guardado de clasves
    String valorusuario = "admin";
    String valorclave = "123456";
    final String VAL_PREFERENCIA = "MiPreferencia";
    final String KEY_USUARIO = "KeyUsuario";
    final String KEY_CLAVE = "KeyClave";
    final String KEY_CHECK="KeyCheck";
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);
        //INICIA EL TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //

        final TextInputEditText etUsuario = findViewById(R.id.etUsuario);
        final TextInputEditText etClave = findViewById(R.id.etClave);
        final CheckBox checkBox = findViewById(R.id.checkBox);
        final Button btnIngresar = findViewById(R.id.btnIngresar);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.print("Estamos en el click");
                String username = etUsuario.getText().toString();
                String password = etClave.getText().toString();

                if (username.equals("") && password.equals("")){
                    Toast.makeText(getApplicationContext(),"Por favor ingrese usuario/clave",Toast.LENGTH_SHORT).show();
                }
                else {
                    String url = "http://10.143.143.247:53845/Login/login";

                    try{
                        System.out.print("Entro Al JsonObject");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("usuario", username);
                        jsonObject.put("password", password);

                        RequestQueue requestQueue = Volley.newRequestQueue(Logueo.this);
                        JsonObjectRequest objectRequest = new JsonObjectRequest(
                                Request.Method.POST,
                                url,
                                jsonObject,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                    try {
                                        System.out.print("Entro Al TRY del JsonObject");
                                        Usuario usu = new Usuario();
                                        usu.setUsername(response.getString("usuario").toString());
                                        usu.setNombreCompleto(response.getString("nombreCompleto").toString());
                                        usu.setApellidoPaterno(response.getString("apellidoPaterno").toString());
                                        usu.setApellidoMaterno(response.getString("apellidoMaterno").toString());

                                        Control.getMiInstancia().miUsuario=usu;

                                        Intent intent = new Intent(Logueo.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }catch (Exception e){
                                        System.out.print("Primer cath!!");
                                        e.printStackTrace();
                                    }
                                    }
                                },
                                new ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        System.out.print("Segundo cath!!");
                                        System.out.print("Estamos en el puto error volley");
                                    }
                                }
                        );

                        requestQueue.add(objectRequest);
                    }
                    catch (Exception e){
                        System.out.print("Estamos en el Tercer cath");
                        e.printStackTrace();

                    }

                }








            }
        });
    }



    /*
    public void onClick(View view) {
                String usuario = etUsuario.getText().toString();
                String clave = etClave.getText().toString();





                //CODIGO PARA VALIDAR EL ACCESO
                if (usuario.equals(valorusuario)  && clave.equals(valorclave)){
                    SharedPreferences miPreferencia = getSharedPreferences(VAL_PREFERENCIA,MODE_PRIVATE);
                    SharedPreferences.Editor miEditor  = miPreferencia.edit();
                    if (checkBox.isChecked()){
                        miEditor.putString(KEY_USUARIO,usuario);
                        miEditor.putString(KEY_CLAVE,clave);
                        miEditor.putBoolean(KEY_CHECK,true);
                    }else {
                        miEditor.remove(KEY_USUARIO);
                        miEditor.remove(KEY_CLAVE);
                        miEditor.remove(KEY_CHECK);
                    }
                    miEditor.apply();

                    Intent intent = new Intent(Logueo.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Logueo.this,"Error al ingresar Usuario|Contraseña",Toast.LENGTH_SHORT).show();
                }

                SharedPreferences valoresPreferencia = getSharedPreferences(VAL_PREFERENCIA,MODE_PRIVATE);
                String usuarioGuardado = valoresPreferencia.getString(KEY_USUARIO,"");
                String claveGuarddado = valoresPreferencia.getString(KEY_CLAVE,"");
                Boolean chekGuardado = valoresPreferencia.getBoolean(KEY_CHECK,false);

                etUsuario.setText(usuarioGuardado);
                etClave.setText(claveGuarddado);
                checkBox.setChecked(chekGuardado);
            }
     */

}
