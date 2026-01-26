package com.example.a46masrawyasset;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btnCargarR;
    Button btnGuardarR;
    Button btnCargarA;
    Button btnGuardarA;

    MediaPlayer mediaPlayer;
    ImageButton imageButton;

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

        btnCargarR = findViewById(R.id.btnCargarR);
        btnGuardarR = findViewById(R.id.btnGuardarR);
        btnCargarA = findViewById(R.id.btnCargarA);
        btnGuardarA = findViewById(R.id.btnGuardarA);
        imageButton = findViewById(R.id.btnReproducirRaw);

        ImageView imageView = findViewById(R.id.imageViewAsset);

        btnCargarR.setOnClickListener(v -> reproducirAudioRaw(R.raw.musica_fondo));
        btnCargarA.setOnClickListener(v -> cargarImagenAsset("botswana.png", imageView));
        
        btnGuardarA.setOnClickListener(v-> guardarAudioInterno("audio.mp3", mediaPlayer));
    }


    public void reproducirAudioRaw(int idRecurso) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, idRecurso);
        // controloes

        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(mp -> {
            mp.release();
            mediaPlayer = null;
        });
    }

    public void cargarImagenAsset(String nombreImagen, ImageView imageView) {
        try {
            InputStream is = getAssets().open(nombreImagen);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(bitmap);
            is.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // guardar imagen en interno
    public void guardarImagenInterna(String nombreArchivo, Bitmap imagen) {
        try (FileOutputStream fos = openFileOutput(nombreArchivo, Context.MODE_PRIVATE)) {
            imagen.compress(Bitmap.CompressFormat.PNG, 100, fos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // guardar imagen en interno   TODO EN EXAMEN PUEDEN PEDIR EN EXTERNO HAY QUE VERLO

    public void guardarAudioInterno(String nombreArchivo, byte[] datosAudio) {
        try (FileOutputStream fos = openFileOutput(nombreArchivo, Context.MODE_PRIVATE)){
            fos.write(datosAudio);
            Toast.makeText(this, "Audio guardado en Interno", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}