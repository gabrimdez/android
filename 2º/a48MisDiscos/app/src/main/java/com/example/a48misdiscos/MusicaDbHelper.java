package com.example.a48misdiscos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicaDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Musica.db";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla usando tipos de Von Neumann (TEXT e INTEGER)
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MusicaContrato.NOMBRE_TABLA + " (" +
                    MusicaContrato.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MusicaContrato.COL_NOMBRE + " TEXT," +
                    MusicaContrato.COL_ARTISTA + " TEXT," +
                    MusicaContrato.COL_GENERO + " TEXT," +
                    MusicaContrato.COL_DURACION + " INTEGER," +
                    MusicaContrato.COL_ANIO + " INTEGER)";

    public MusicaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MusicaContrato.NOMBRE_TABLA);
        onCreate(db);
    }

    // Método para insertar (Como llenar un vector de 50 posiciones)
    public long insertarCancion(String nombre, String artista, String genero, int duracion, int anio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MusicaContrato.COL_NOMBRE, nombre);
        values.put(MusicaContrato.COL_ARTISTA, artista);
        values.put(MusicaContrato.COL_GENERO, genero);
        values.put(MusicaContrato.COL_DURACION, duracion);
        values.put(MusicaContrato.COL_ANIO, anio);

        return db.insert(MusicaContrato.NOMBRE_TABLA, null, values);
    }
}