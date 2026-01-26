package com.example.a29menucontextual;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ActionMode mActionMode;
    ImageView imageView;

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

        imageView = findViewById(R.id.imageView);
        imageView.setOnLongClickListener(v -> {
            if (mActionMode != null) {
                return false;
            }
            mActionMode = startActionMode(mActionModeCallback);
            return true;
        });

    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.menu_cast) {
                Toast.makeText(MainActivity.this, "HAS PULSADO Cast", Toast.LENGTH_LONG).show();
                mode.finish();
                return true;
            } else if (id == R.id.menu_print) {
                Toast.makeText(MainActivity.this, "HAS PULSADO PRINT", Toast.LENGTH_LONG).show();
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    };


}