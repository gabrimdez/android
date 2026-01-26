package com.example.a07navigationdrawer.ui.icononuevo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IconoMoroModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public IconoMoroModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is PUTOS MOROS fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
