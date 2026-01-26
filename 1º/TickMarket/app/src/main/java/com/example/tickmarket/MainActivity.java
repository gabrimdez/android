package com.example.tickmarket;

import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(); // Añadir la toolbar

        // Setear adaptador al viewpager.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager);

        // Preparar las pestañas
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        tabs.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_shop);

        if (item != null && item.getIcon() != null) {
            android.graphics.drawable.Drawable raw = item.getIcon();
            android.graphics.drawable.LayerDrawable iconLayer = null;

            if (raw instanceof android.graphics.drawable.LayerDrawable) {
                iconLayer = (android.graphics.drawable.LayerDrawable) raw;
            } else {
                // Envolver el drawable original en un LayerDrawable con una capa adicional para la badge
                android.graphics.drawable.Drawable[] layers = new android.graphics.drawable.Drawable[2];
                layers[0] = raw;
                layers[1] = new BadgeDrawable(this);
                iconLayer = new android.graphics.drawable.LayerDrawable(layers);
                // Asignar ids de capa coherentes con el layer-list esperado
                try {
                    iconLayer.setId(0, R.id.ic_notification);
                    iconLayer.setId(1, R.id.ic_badge);
                } catch (Exception e) {
                    // no crítico, continuar
                }
                // Reemplazar el icono del menu por el nuevo LayerDrawable
                item.setIcon(iconLayer);
            }

            if (iconLayer != null) {
                Utils.setBadgeCount(this, iconLayer, 3);
            }
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_shop) {
            showSnackBar("Carrito de compras");
            return true;
        }

        if (id == R.id.action_settings){
            showSnackBar("Configuration");
            this.finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Muestra una {@link Snackbar} prefabricada
     *
     * @param msg Mensaje a proyectar
     */
    private void showSnackBar(String msg) {
        Snackbar.make(findViewById(R.id.fab), msg, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Establece la toolbar como action bar
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    /**
     * Crea una instancia del view pager con los datos
     * predeterminados
     *
     * @param viewPager Nueva instancia
     */
    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(GridFragment.newInstance(1), getString(R.string.title_section1));
        adapter.addFragment(GridFragment.newInstance(2), getString(R.string.title_section2));
        adapter.addFragment(GridFragment.newInstance(3), getString(R.string.title_section3));
        viewPager.setAdapter(adapter);
    }

    /**
     * Método onClick() del FAB
     *
     * @param v View presionado
     */
    public void onFabClick(View v) {
        showSnackBar("Buscar producto");
    }

    /**
     * Un {@link FragmentPagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
