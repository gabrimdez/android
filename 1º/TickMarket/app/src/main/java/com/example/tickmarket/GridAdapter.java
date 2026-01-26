package com.example.tickmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * {@link android.widget.BaseAdapter} personalizado para el gridview
 */
public class GridAdapter extends BaseAdapter {

    private final Context mContext;
    private final Product[] items;

    public GridAdapter(Context c, Product[] items) {
        mContext = c;
        this.items = items;
    }

    @Override
    public int getCount() {
        // Devolver el número real de items (protegido)
        return (items != null) ? items.length : 0;
    }

    @Override
    public Product getItem(int position) {
        if (items == null || items.length == 0) return null;
        if (position < 0) position = 0;
        if (position >= items.length) position = items.length - 1;
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_header, viewGroup, false);
        }

        Product item = getItem(position);
        if (item == null) return view;

        // Seteando Imagen
        ImageView image = view.findViewById(R.id.imagen);
        if (image != null) Glide.with(image.getContext()).load(item.getIdThumbnail()).into(image);

        // Seteando Nombre
        TextView name = view.findViewById(R.id.nombre);
        if (name != null) name.setText(item.getNombre());

        // Seteando Descripción
        TextView descripcion = view.findViewById(R.id.descripcion);
        if (descripcion != null) descripcion.setText(item.getDescripcion());

        // Seteando Precio
        TextView precio = view.findViewById(R.id.precio);
        if (precio != null) precio.setText(item.getPrecio());

        // Seteando Rating
        RatingBar ratingBar = view.findViewById(R.id.rating);
        if (ratingBar != null) ratingBar.setRating(item.getRating());

        return view;
    }
}
