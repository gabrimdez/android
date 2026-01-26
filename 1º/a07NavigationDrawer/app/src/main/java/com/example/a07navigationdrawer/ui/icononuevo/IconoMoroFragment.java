package com.example.a07navigationdrawer.ui.icononuevo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a07navigationdrawer.databinding.FragmentIconomoroBinding;

public class IconoMoroFragment extends Fragment {

    private FragmentIconomoroBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {

        IconoMoroModel iconoMoroModel =
                new ViewModelProvider(this).get(IconoMoroModel.class);

        binding = FragmentIconomoroBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        final TextView textView = binding.moritos;
        iconoMoroModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
