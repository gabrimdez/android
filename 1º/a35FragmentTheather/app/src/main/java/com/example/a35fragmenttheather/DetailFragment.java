package com.example.a35fragmenttheather;

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

    public static final String KEY_OBRA_NAME = "KEY_OBRA_NAME";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(KEY_OBRA_NAME)) {
            showSelectedObra(bundle.getString(KEY_OBRA_NAME));
        }
    }

    public void showSelectedObra(String obraName) {
        if (getView() != null) {
            TextView textViewObraName = getView().findViewById(R.id.textViewCountryName);
            if (textViewObraName != null) {
                textViewObraName.setText(obraName);
            }
        } else {
            Toast.makeText(getActivity(), "getView() is null", Toast.LENGTH_SHORT).show();
        }
    }
}
