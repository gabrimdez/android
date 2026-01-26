package com.example.a06navigationinferior.ui.meme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a06navigationinferior.databinding.FragmentMemeBinding;

public class MemeFragment extends Fragment {

    private FragmentMemeBinding binding ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        MemeViewModel memeViewModel = new ViewModelProvider(this).get(MemeViewModel.class);

        binding = FragmentMemeBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        final TextView textView = binding.textView;
        memeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
