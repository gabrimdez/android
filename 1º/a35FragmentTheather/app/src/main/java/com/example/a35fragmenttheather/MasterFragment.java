package com.example.a35fragmenttheather;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class MasterFragment extends ListFragment {

    private OnMasterSelectedListener mOnMasterSelectedListener = null;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] countries = new String[]{
                "China",
                "France",
                "Germany",
                "India",
                "Russia",
                "United Kingdom",
                "United States"
        };

        ListAdapter obrasAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1, countries
        );

        setListAdapter(obrasAdapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnMasterSelectedListener != null && view instanceof TextView) {
                    String titulo = ((TextView) view).getText().toString();
                    mOnMasterSelectedListener.onItemSelected(titulo);
                }
            }
        });
    }

    public void setOnMasterSelectedListener(OnMasterSelectedListener listener) {
        mOnMasterSelectedListener = listener;
    }

    public interface OnMasterSelectedListener {
        void onItemSelected(String titulo);
    }
}
