package com.example.a34pasingdataconfragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    public static String KEY_CONTRY_NAME = "KEY_CONTRY_NAME";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(KEY_CONTRY_NAME)) {
            showSelectedCountry(bundle.getString(KEY_CONTRY_NAME));
        }
    }

    public void showSelectedCountry(String countryName) {
        TextView textViewCpuntryName = null;
        if (getView() != null) {
            textViewCpuntryName = getView().findViewById(R.id.textViewCountryName);
        } else {
            Toast.makeText(getActivity(), "getView() is null", Toast.LENGTH_SHORT).show();
        }

        textViewCpuntryName.setText(countryName);
    }
}
