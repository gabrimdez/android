package com.example.a36fragmentconviewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PetsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Binding (enlace)
        mDataBinding = DataBindingUtil.inflate(inflater,
                R.layout.pets_frag, container, false);

        // ViewModel
        PetsViewModel.Factory factory = new PetsViewModel.Factory(
                requireActivity(),
                null,
                DefaultPetsRepository.getInstance()
        );
        mViewModel = new ViewModelProvider(requireActivity(), factory).get(PetsViewModel.class);

        mDataBinding.setLifecycleOwner(getViewLifecycleOwner());

        return mDataBinding.getRoot();
    }

}
