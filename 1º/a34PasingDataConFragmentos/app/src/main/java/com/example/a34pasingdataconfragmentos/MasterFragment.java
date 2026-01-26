package com.example.a34pasingdataconfragmentos;

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


/**
 * A fragment representing a list of Items.
 */
public class MasterFragment extends ListFragment {

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

        ListAdapter countryAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, countries
        );

        setListAdapter(countryAdapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnMasterSelectedListener != null){
                    mOnMasterSelectedListener.onItemSelected( ( (TextView) view).getText().toString());
                }
            }
        });
    }

    // instancia de la interfaz
    private OnMasterSelectedListenner mOnMasterSelectedListener = null;
    // metodo que setea la interfaz
    public void setOnMasterSelectedListener(OnMasterSelectedListenner listener) {
        mOnMasterSelectedListener = listener;
    }






    public interface OnMasterSelectedListenner {
        void onItemSelected(String contryName);
    }


}