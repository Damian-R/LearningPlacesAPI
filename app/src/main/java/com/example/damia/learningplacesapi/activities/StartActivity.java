package com.example.damia.learningplacesapi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.damia.learningplacesapi.R;
import com.example.damia.learningplacesapi.fragments.StartFragment;

public class StartActivity extends AppCompatActivity {

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
}
