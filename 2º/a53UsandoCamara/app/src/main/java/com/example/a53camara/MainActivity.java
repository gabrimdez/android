package com.example.a53camara;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    static final int CARGAR_IMAGEN = 1;
    static final int TOMAR_FOTO = 2;
    ImageView imageView;
    String fotoPath;
    Button sacar;
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

        imageView = findViewById(R.id.imageView);
        sacar = findViewById(R.id.button);
        guardar = findViewById(R.id.button2);
        sacar.setOnClickListener(v -> sacarFoto(v));
        guardar.setOnClickListener(v -> guardarFoto(v));
    }

        private void sacarFoto(View v) {
            // Lógica para tomar una foto usando la cámara
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //SOlo lanzamos el intent no intentamos leer la foto aqui
            //Quitamos la busqueda de un paquete
            //if(intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, CARGAR_IMAGEN);

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Los fatos vienen en el parametro 'data' que recibe este metodo
        if (requestCode == CARGAR_IMAGEN && resultCode == RESULT_OK) {
            //Obtenemos la foto tomada
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void guardarFoto(View view) {
            // Lógica para guardar la foto tomada
            if (imageView == null || imageView.getDrawable() == null) {
                Toast.makeText(this, "Captura una imagen primero", Toast.LENGTH_SHORT).show();
                return;
            }
            Bitmap miBitMap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            //ver save Image
            if (miBitMap != null) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DISPLAY_NAME, "capturada_imagen_" + System.currentTimeMillis());
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                values.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/CamaraApp");

                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                try (OutputStream outputStream = getContentResolver().openOutputStream(uri)) {
                    miBitMap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    Toast.makeText(this, "Imagen guardada con exito", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Captura una imagen primero", Toast.LENGTH_SHORT).show();
            }

        }
}