package com.example.a54video;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView; // Cambiado ImageView por VideoView para videos

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    ///storage/emulated/0/Movies/CamaraApp/video_cap_1771170222297.mp4

    static final int GRABAR_VIDEO = 1;
    VideoView videoView; // Cambiado de ImageView
    Uri videoUri;        // Almacenaremos la ubicación temporal del video
    Button grabar;
    Button guardar;

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

        videoView = findViewById(R.id.videoView); // Asegúrate de cambiar el ID en el XML
        grabar = findViewById(R.id.button);
        guardar = findViewById(R.id.button2);

        grabar.setOnClickListener(v -> grabarVideo(v));
        guardar.setOnClickListener(v -> guardarVideo(v));
    }

    private void grabarVideo(View v) {
        // ACTION_VIDEO_CAPTURE lanza la cámara en modo video
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, GRABAR_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GRABAR_VIDEO && resultCode == RESULT_OK) {
            // A diferencia de las fotos (Bitmap), los videos devuelven un URI
            videoUri = data.getData();
            videoView.setVideoURI(videoUri);
            videoView.start(); // Reproduce una vista previa automática
        }
    }

    private void guardarVideo(View view) {
        if (videoUri == null) {
            Toast.makeText(this, "Graba un video primero", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(MediaStore.Video.Media.DISPLAY_NAME, "video_cap_" + System.currentTimeMillis() + ".mp4");
        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        values.put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/CamaraApp");

        Uri uriDestino = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);

        try (InputStream inputStream = getContentResolver().openInputStream(videoUri);
             OutputStream outputStream = getContentResolver().openOutputStream(uriDestino)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            Toast.makeText(this, "Video guardado con éxito", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar video", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}