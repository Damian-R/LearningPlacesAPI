package com.example.damia.learningplacesapi.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.damia.learningplacesapi.R;
import com.example.damia.learningplacesapi.fragments.StartFragment;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class StartActivity extends AppCompatActivity implements StartFragment.onButtonPressedListener{

    private int PLACE_PICKER_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        StartFragment startFragment = (StartFragment)getSupportFragmentManager().findFragmentById(R.id.start_container);
        if(startFragment == null){
            startFragment = StartFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.start_container, startFragment).commit();
        }


    }

    @Override
    public void buttonPressed() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (Exception e){

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.v("Maps", "Places loaded");

        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }

    }
}
