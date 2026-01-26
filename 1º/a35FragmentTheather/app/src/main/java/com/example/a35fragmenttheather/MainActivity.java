package com.example.a35fragmenttheather;

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

        FrameLayout frameLayout = findViewById(R.id.frameLayout);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MasterFragment masterFragment;

        if (frameLayout != null) {
            mDualPanel = false;

            masterFragment = (MasterFragment) getSupportFragmentManager().findFragmentByTag("MASTER");
            if (masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayout, masterFragment, "MASTER");
            }

            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().
                    findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment != null) {
                fragmentTransaction.remove(detailFragment);
            }

            fragmentTransaction.commit();

        } else {
            mDualPanel = true;

            masterFragment = (MasterFragment) getSupportFragmentManager().
                    findFragmentById(R.id.frameLayoutMaster);
            if (masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayoutMaster, masterFragment);
            }

            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().
                    findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment == null) {
                detailFragment = new DetailFragment();
                fragmentTransaction.add(R.id.frameLayoutDetail, detailFragment);
            }

            fragmentTransaction.commit();
        }

        // Set listener
        masterFragment.setOnMasterSelectedListener(new MasterFragment.OnMasterSelectedListener() {
            @Override
            public void onItemSelected(String countryName) {
                sendCountryName(countryName);
            }
        });
    }

    private void sendCountryName(String country) {
        DetailFragment detailFragment;
        if (mDualPanel) {
            detailFragment = (DetailFragment) getSupportFragmentManager().
                    findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment != null) {
                detailFragment.showSelectedObra(country);
            }
        } else {
            detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailFragment.KEY_OBRA_NAME, country);
            detailFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, detailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
