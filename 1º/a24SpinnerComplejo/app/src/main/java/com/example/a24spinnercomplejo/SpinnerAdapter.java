package com.example.a24spinnercomplejo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<Team> {

    ArrayList<Team> list;

    public SpinnerAdapter(@NonNull Context context, ArrayList<Team> list) {
        super(context, 0, list);
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Devolver la vista personalizada para el elemento seleccionado
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Devolver la vista personalizada para el dropdown
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = View.inflate(getContext(),R.layout.elemento,null);
        }

        Team team = list.get(position);
        TextView nameView = view.findViewById(R.id.name);
        nameView.setText(team.getName());

        TextView city = view.findViewById(R.id.city);
        city.setText(team.getCity());

        TextView country = view.findViewById(R.id.country);
        country.setText(team.getCountry());

        TextView year = view.findViewById(R.id.year);
        // Evitar llamar setText(int) con un año (sería interpretado como resource id)
        year.setText(String.valueOf(team.getYears()));

        ImageView image = view.findViewById(R.id.crest);
        image.setImageResource(team.getCrest());

        return view;
    }
}
