package com.example.cosc341_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import render.animations.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    Button planAnEventButton;
    Button viewEventsButton;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<ArrayList<String>> food = new ArrayList<>();
    ArrayList<ArrayList<String>> drinks = new ArrayList<>();
    ArrayList<ArrayList<String>> games = new ArrayList<>();
    ArrayList<ArrayList<String>> decorations = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for the background animation

        ConstraintLayout constraintLayout = findViewById(R.id.layoutMain);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);
        Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1000);

        final Intent choose = new Intent(this,ChooseActivity.class);
        final Intent browse = new Intent(this, BrowseActivity.class);




        planAnEventButton = (Button) (findViewById(R.id.button_planAnEvent));
        viewEventsButton = (Button) (findViewById(R.id.button_BrowseAvailableEvents));

        planAnEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(choose);

            }
        });

        viewEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                boolean goNext = true;
                Toast toast;


                File file = new File("/data/data/com.example.cosc341_project/files/output.txt");
                if(file.length() == 0) {
                    goNext = false;
                    toast = Toast.makeText(context, "No events found, please add some.", Toast.LENGTH_SHORT);
                    toast.show();
                }

                if(goNext){
                    startActivity(browse);
                }

            }
        });


    }
}
