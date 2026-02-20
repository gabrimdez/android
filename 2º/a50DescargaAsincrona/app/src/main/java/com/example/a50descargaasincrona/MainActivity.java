package com.example.a50descargaasincrona;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText etUrl;
    private ImageView imageView;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

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

        etUrl = findViewById(R.id.etUrl);
        imageView = findViewById(R.id.imageView);
        Button btnDownload = findViewById(R.id.button);

        btnDownload.setOnClickListener(v -> {
            String url = etUrl.getText().toString();
            if (!url.isEmpty()) {
                downloadImage(url);
            } else {
                Toast.makeText(this, "Introduce una URL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadImage(String urlString) {
        executor.execute(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);

                // Carpeta específica de la app en almacenamiento externo
                File storageDir = getExternalFilesDir(null); // tipo app-specific, no necesita permiso.[web:23][web:17]
                if (storageDir == null) {
                    throw new Exception("No se pudo obtener getExternalFilesDir");
                }

                // 1) Guardar imagen
                File imageFile = new File(storageDir, "imagen_descargada.jpg");
                FileOutputStream out = new FileOutputStream(imageFile);
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.close();

                // 2) Crear txt con la ruta de la imagen
                File txtFile = new File(storageDir, "ruta_imagen.txt");
                try (FileWriter writer = new FileWriter(txtFile, false)) { // sobrescribe cada vez.[web:22]
                    writer.write(imageFile.getAbsolutePath());
                }

                handler.post(() -> {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
                    Toast.makeText(
                            MainActivity.this,
                            "Imagen en: " + imageFile.getAbsolutePath() +
                                    "\nTXT en: " + txtFile.getAbsolutePath(),
                            Toast.LENGTH_LONG
                    ).show();
                });

            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() ->
                        Toast.makeText(this, "Error al descargar", Toast.LENGTH_SHORT).show()
                );
            }
        });
    }
}
