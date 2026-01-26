package com.example.tickmarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Un fragmento que contiene una grilla de productos
 */
public class GridFragment extends Fragment {
    /**
     * Argumento que representa el número sección al que pertenece
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Creación prefabricada de un {@link GridFragment}
     */
    public static GridFragment newInstance(int sectionNumber) {
        GridFragment fragment = new GridFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout correcto que contiene el GridViewWithHeaderAndFooter
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        // Obtención del grid view
        GridViewWithHeaderAndFooter grid =
                rootView.findViewById(R.id.pager);

        // Inicializar el grid view
        setUpGridView(grid);

        return rootView;
    }

    /**
     * Infla el grid view del fragmento dependiendo de la sección
     *
     * @param grid Instancia del grid view
     */
    private void setUpGridView(GridViewWithHeaderAndFooter grid) {
        // Proteger contra NullPointerException si no se pasaron argumentos
        Bundle args = getArguments();
        int section_number = (args != null) ? args.getInt(ARG_SECTION_NUMBER, 1) : 1;
        switch (section_number) {
            case 1:
                grid.addHeaderView(createHeaderView(grid, 6, Products.getTelefonos()));
                grid.setAdapter(new GridAdapter(getActivity(), Products.getTelefonos()));
                break;
            case 2:
                grid.addHeaderView(createHeaderView(grid, 6, Products.getTablets()));
                grid.setAdapter(new GridAdapter(getActivity(), Products.getTablets()));
                break;
            case 3:
                grid.addHeaderView(createHeaderView(grid, 6, Products.getPortatiles()));
                grid.setAdapter(new GridAdapter(getActivity(), Products.getPortatiles()));
                break;
        }
    }

    /**
     * Crea un view de cabecera para mostrarlo en el principio del grid view.
     *
     * @param parent   Parent para inflar correctamente los layout params
     * @param position Posición del item que sera el grid view dentro de {@code items}
     * @param items    Array de productos
     * @return Header View
     */
    private View createHeaderView(ViewGroup parent, int position, Product[] items) {

        View view;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        // Clamp position para evitar IndexOutOfBounds
        int pos = 0;
        if (items != null && items.length > 0) {
            pos = Math.min(Math.max(0, position), items.length - 1);
        }

        view = inflater.inflate(R.layout.grid_header, parent, false);

        Product item = (items != null && items.length > 0) ? items[pos] : null;

        // Seteando Imagen
        ImageView image = view.findViewById(R.id.imagen);
        if (image != null && item != null) {
            Glide.with(image.getContext()).load(item.getIdThumbnail()).into(image);
        }

        // Seteando Nombre
        TextView name = view.findViewById(R.id.nombre);
        if (name != null && item != null) name.setText(item.getNombre());

        // Seteando Descripción
        TextView descripcion = view.findViewById(R.id.descripcion);
        if (descripcion != null && item != null) descripcion.setText(item.getDescripcion());

        // Seteando Precio
        TextView precio = view.findViewById(R.id.precio);
        if (precio != null && item != null) precio.setText(item.getPrecio());

        // Seteando Rating
        RatingBar ratingBar = view.findViewById(R.id.rating);
        if (ratingBar != null && item != null) ratingBar.setRating(item.getRating());

        return view;
    }
}
