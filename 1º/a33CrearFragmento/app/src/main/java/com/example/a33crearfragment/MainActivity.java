package com.example.a33crearfragment;

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

    FragmentOne fragmentOne;
    Button boton1,boton2;
    FragmentContainerView fragmentContainerView;

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
        boton1=findViewById(R.id.button);
        boton2=findViewById(R.id.button2);

        boton1.setOnClickListener(v -> {
            irFragmentoUno(v);
        });
        boton2.setOnClickListener(v -> {
            irFragmentoDos(v);
        });
        }

        public void irFragmentoUno(View view) {
            fragmentOne = new FragmentOne();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragmentOne)
                    .commit();
        }
        public void irFragmentoDos(View view) {
            FragmentTwo fragmentTwo = new FragmentTwo();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragmentTwo)
                    .commit();
        }
    }
