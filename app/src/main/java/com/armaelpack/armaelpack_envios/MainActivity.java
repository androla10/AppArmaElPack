package com.armaelpack.armaelpack_envios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Usuario;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //ASIGNAMOS EL TOOLBAR AL PROYECTO
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Usuario usuario = Control.getMiInstancia().miUsuario;

        TextView tvUserName = findViewById(R.id.tvusername);
        TextView tvNombreCompleto = findViewById(R.id.tvnombreCompleto);
        TextView tvApellidoPaterno = findViewById(R.id.tvapellidoPaterno);
        TextView tvApellidoMaterno = findViewById(R.id.tvapellidoMaterno);


        //Llamamos al TOOLBAR
        TextView tvNombreToolbarTrans = findViewById(R.id.tvNombreToolbar);
        tvNombreToolbarTrans.setText("Hola: "+ usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno()+", "+usuario.getNombreCompleto());
        toolbar.setTitle("Holaaa");







        tvUserName.setText(usuario.getUsername());
        tvNombreCompleto.setText(usuario.getNombreCompleto());
        tvApellidoPaterno.setText(usuario.getApellidoPaterno());
        tvApellidoMaterno.setText(usuario.getApellidoMaterno());



        }

}
