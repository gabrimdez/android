package com.example.a03lifecyrcle;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView estado;

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
        ((TextView) findViewById(R.id.estado)).append("onCreate()\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((TextView)findViewById(R.id.estado)).append("onStart()\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView)findViewById(R.id.estado)).append("onResume()\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((TextView)findViewById(R.id.estado)).append("onPause()\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((TextView)findViewById(R.id.estado)).append("onStop()\n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ((TextView)findViewById(R.id.estado)).append("onRestart()\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((TextView)findViewById(R.id.estado)).append("onDestroy()\n");
    }
}