package com.example.a06navigationinferior.ui.meme;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MemeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MemeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is meme fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
