package com.example.a36fragmentconviewmodel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class PetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //...
        mViewModel = new ViewModelProvider(this, factory).get(PetsViewModel.class);

    }
}

public class PetsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //...
        mViewModel = new ViewModelProvider(requireActivity(), factory).get(PetsViewModel.class);
    }
}

public class PetDetailFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //...
        mViewModel = new ViewModelProvider(requireActivity(), factory).get(PetsViewModel.class);
    }
}