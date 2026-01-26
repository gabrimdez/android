package com.example.a33crearfragment;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

public class MainActivity extends AppCompatActivity {


    Button boton;
    Button boton2;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;

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

        boton = findViewById(R.id.button);
        boton2 = findViewById(R.id.button2);

        boton.setOnClickListener(v -> {
            fragmentOne = new FragmentOne();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragmentOne).commit();
        });

        boton2.setOnClickListener(v -> {
            fragmentTwo = new FragmentTwo();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragmentTwo).commit();
        });
    }
}