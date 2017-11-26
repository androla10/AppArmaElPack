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

        TextView tvUserName = findViewById(R.id.tvusername);
        TextView tvNombreCompleto = findViewById(R.id.tvnombreCompleto);
        TextView tvApellidoPaterno = findViewById(R.id.tvapellidoPaterno);
        TextView tvApellidoMaterno = findViewById(R.id.tvapellidoMaterno);


        Usuario usuario = Control.getMiInstancia().miUsuario;

        tvUserName.setText(usuario.getUsername());
        tvNombreCompleto.setText(usuario.getNombreCompleto());
        tvApellidoPaterno.setText(usuario.getApellidoPaterno());
        tvApellidoMaterno.setText(usuario.getApellidoMaterno());




        }

}
