package com.example.a34pasingdataconfragmentos;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {


    boolean mDualPanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MasterFragment masterFragment = null;
        FrameLayout frameLayout = findViewById(R.id.frameLayout);

        if (frameLayout != null) {
            mDualPanel = false;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (MasterFragment) getSupportFragmentManager().findFragmentByTag("MASTER");
            if (masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayout, masterFragment, "MASTER");
            }
            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);

            if (detailFragment != null) {
                fragmentTransaction.remove(detailFragment);
            }

            fragmentTransaction.commit();
        } else {
            mDualPanel = true;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (MasterFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutMaster);

            if (masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayoutMaster, masterFragment);
            }

            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment == null) {
                detailFragment = new DetailFragment();
                fragmentTransaction.add(R.id.frameLayoutDetail, detailFragment);
            }


            fragmentTransaction.commit();
        }

        masterFragment.setOnMasterSelectedListener(new MasterFragment.OnMasterSelectedListenner() {
            @Override
            public void onItemSelected(String contryName) {
                sendCountryName(contryName);
            }
        });
    }

    private void sendCountryName(String country) {
        DetailFragment detailFragment;
        if (mDualPanel) {
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            detailFragment.showSelectedCountry(country);
        } else {
            detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailFragment.KEY_CONTRY_NAME, country);

            detailFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, detailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    }
}