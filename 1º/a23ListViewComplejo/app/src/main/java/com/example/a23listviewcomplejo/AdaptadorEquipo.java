package com.example.a23listviewcomplejo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorEquipo extends ArrayAdapter<Equipo> {

    private List<Equipo> listaEquipos;
    private int layoutRestId;


    public AdaptadorEquipo(@NonNull Context context, int layoutRestId, List<Equipo> listaEquipos) {
        super(context, layoutRestId, listaEquipos);
        this.listaEquipos = listaEquipos;
        this.layoutRestId = layoutRestId;
    }

    @Override
    public int getCount() {
        return listaEquipos.size();
    }

    @Override
    public Equipo getItem(int position) {
        return listaEquipos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.equipo,parent,false);
        }
        TextView clubName = view.findViewById(R.id.tv_clubName);
        TextView clubCity = view.findViewById(R.id.tv_city);
        TextView clubCountry = view.findViewById(R.id.tv_country);
        TextView clubFoundation = view.findViewById(R.id.tv_year);
        ImageView image = view.findViewById(R.id.imageView);

        Equipo elemento = listaEquipos.get(position);

        clubName.setText(elemento.getNombre());
        clubCountry.setText(elemento.getPais());
        clubCity.setText(elemento.getCiudad());
        clubFoundation.setText(String.valueOf(elemento.getFundacion()));
        image.setImageResource(elemento.getIdImage());

        view.setOnClickListener(view1 -> {
            listaEquipos.remove(elemento);
            notifyDataSetChanged();
        });

        return view;
    }


    public void addEquipo(Equipo equipo) {

        listaEquipos.add(equipo);
        // notificar cambio para que la ListView se refresque
        notifyDataSetChanged();

    }
}
