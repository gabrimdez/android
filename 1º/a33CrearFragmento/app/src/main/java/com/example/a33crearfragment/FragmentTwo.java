package com.example.a33crearfragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

public class FragmentTwo extends Fragment {

    @Override
    public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
}
