package com.example.a28anidandomenusopciones;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final int MENU_DOWNLOAD = 1;
    private final int MENU_SETTINGS = 2;
    private boolean showDownloadMenu = false;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button);
        button.setOnClickListener( v -> toggleMenu(v));

    }



    public void toggleMenu(View view) {
        showDownloadMenu = !showDownloadMenu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0, MENU_DOWNLOAD, 0, R.string.menu_dowloads);
        menu.add(0, MENU_SETTINGS, 0, R.string.menu_settings);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(MENU_DOWNLOAD);
        menuItem.setVisible(showDownloadMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.uno) {
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartir");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Compartir con"));
            return true;

        } else if (id == R.id.dos) {
            Intent callintent = new Intent();
            callintent.setAction(Intent.ACTION_DIAL);
            callintent.setData(Uri.parse("tel:1234567"));
            return true;
        } else if (id == R.id.tres) {
            Intent webIntent = new Intent();
            webIntent.setAction(Intent.ACTION_VIEW);
            webIntent.setData(Uri.parse("https://www.cebem.net"));
            startActivity(webIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}