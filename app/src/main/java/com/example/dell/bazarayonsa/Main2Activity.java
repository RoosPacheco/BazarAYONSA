package com.example.dell.bazarayonsa;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.P)
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(""); //Mandar logotipo
        setSupportActionBar(toolbar);

        //SEARCH
        SearchView searchView=findViewById(R.id.searchView);
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        /* Code for changing the textcolor and hint color for the search view */
        searchView.setQueryHint("¿Qué buscas?");
        int id =searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView= (TextView) searchView.findViewById(id);
        textView.setTextColor(R.color.white);
        //searchView.setBackgroundColor(R.color.white);

        //Mostrar promociones
        ImageView imageView2= (ImageView) findViewById(R.id.imageView2);
        imageView2.setImageResource(R.drawable.home_octmuebles);
        ImageView imageView3= (ImageView) findViewById(R.id.imageView3);
        imageView3.setImageResource(R.drawable.home_lavadora);
        ImageView imageView4= (ImageView) findViewById(R.id.imageView4);
        imageView4.setImageResource(R.drawable.home_pantalla);
        ImageView imageView5= (ImageView) findViewById(R.id.imageView5);
        imageView5.setImageResource(R.drawable.home_calefactor);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set the home as default
        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            // Handle the camera action
            fragment = new HomeFragment();
        } else if (id == R.id.nav_catalogo) {

            fragment = new CatalogoFragment();

        } else if (id == R.id.nav_home) {
            fragment = new CatalogoFragment();

        } else if (id == R.id.nav_catalogo) {
            fragment = new CatalogoFragment();

        } else if (id == R.id.nav_promociones) {
            fragment = new CatalogoFragment();

        } else if (id == R.id.nav_pedidos) {
            fragment = new CatalogoFragment();

        } else if (id == R.id.nav_regalos) {
            fragment = new CatalogoFragment();

        } else if (id == R.id.nav_cuenta) {
            fragment = new CatalogoFragment();

        } else if (id == R.id.nav_configuracion) {
            fragment = new CatalogoFragment();

        } else if (id == R.id.nav_serviciocliente) {
            //fragment = new CatalogoFragment();
            Intent intent = new Intent(getApplicationContext(), ServicioCliente.class);
            startActivity(intent);
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }
}
