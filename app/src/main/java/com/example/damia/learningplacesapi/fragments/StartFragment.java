package com.example.damia.learningplacesapi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.damia.learningplacesapi.R;
import com.example.damia.learningplacesapi.activities.MapsActivity;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import static android.app.Activity.RESULT_OK;

public class StartFragment extends Fragment {

    private onButtonPressedListener buttonPressedListener;

    public interface onButtonPressedListener{
        public void buttonPressed();
    }

    public StartFragment() {
        // Required empty public constructor
    }

    public static StartFragment newInstance() {
        StartFragment fragment = new StartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        Button button = (Button)v.findViewById(R.id.start);

        buttonPressedListener = (onButtonPressedListener)getActivity();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressedListener.buttonPressed();
            }
        });

        return v;
    }

}
