package com.armaelpack.armaelpack_envios;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Usuario;

public class PantallaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Fragment_Pendientes.OnFragmentInteractionListener,
Fragment_Enviados.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);


        setTitle("Principal");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView tvNombreDelivery = findViewById(R.id.tvNombreDelivery);
        TextView tvCorreoDelivery=findViewById(R.id.tvCorreoDelivery);
        Usuario usuario = Control.getMiInstancia().miUsuario;
        tvNombreDelivery.setText(usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno()+", "+usuario.getNombreCompleto());
        tvCorreoDelivery.setText(usuario.getUsername());


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


/**ESTOS DOS METODOS SON PARA PODER CONTROLAR EL MENU DE LA ESQUINA SUPERIOR DERECHA(LOGOUT)**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**Se terminan los metodos**/



    /** METODOS DENTRO DEL VIEWDRAWER**/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /**para controlar los fragments**/
        boolean FragmentTransaction = false;
        Fragment fragment=null;


        if (id == R.id.nav_Pendientes) {
            // Handle the camera action
            fragment = new Fragment_Pendientes();
            FragmentTransaction=true;
        } else if (id == R.id.nav_Enviados) {
            fragment = new Fragment_Enviados();
            FragmentTransaction=true;
        }
         /* else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        */

         /**contolando fragments v2**/
         if (FragmentTransaction){
             getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();

             item.setChecked(true);
             getSupportActionBar().setTitle(item.getTitle());
         }
         /****/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**este metodo es para que se puedan comunicar con el fragment.. no se usuara!! **/
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /******/
}
