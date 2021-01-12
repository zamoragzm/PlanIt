package com.example.cosc341_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import pl.droidsonroids.gif.GifImageView;

public class BrowseActivity extends AppCompatActivity {


    Button nextButton;
    Button previousButton;
    GifImageView backButton;
    Button shareButton;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<ArrayList<String>> foodArrays = new ArrayList<>();
    ArrayList<ArrayList<String>> drinksArrays = new ArrayList<>();
    ArrayList<ArrayList<String>> gamesArrays = new ArrayList<>();
    ArrayList<ArrayList<String>> decorationsArrays = new ArrayList<>();
    LinearLayout foodView;
    LinearLayout drinkView;
    LinearLayout gameView;
    LinearLayout decorationsView;
    TextView partyPlanText;
    TextView themeText;
    TextView budgetText;
    int entries;
    int currentEntry;
    String[] lineOfData;
    String numOfGuests;
    String budget;
    String where;
    String when;
    String desc;
    String theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        ConstraintLayout constraintLayout = findViewById(R.id.layoutBrowse);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        final Intent goBack = new Intent(this,MainActivity.class);


        nextButton = (Button) (findViewById(R.id.nextButtonBrowse));
        previousButton = (Button) (findViewById(R.id.previosButtonBrowse));
        backButton = (pl.droidsonroids.gif.GifImageView) (findViewById(R.id.backButtonBrowse));
        shareButton = (Button) (findViewById(R.id.shareBrowseButton));
        foodView = (findViewById(R.id.foodCScrollViewLL));
        drinkView = (findViewById(R.id.drinkcLL));
        gameView = (findViewById(R.id.gamesc2LL));
        decorationsView = (findViewById(R.id.decCLL));
        partyPlanText = (findViewById(R.id.partyPlanTempTextView));
        themeText = (findViewById(R.id.partyThemeTempTextView));
        budgetText = (findViewById(R.id.budgetTempTextView));


        String line;
        try {
            data = new ArrayList<>();
            FileInputStream fis = openFileInput("output.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                data.add(line);
                String[] temp;
                temp =  br.readLine().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                foodArrays.add(new ArrayList<String>(Arrays.asList(temp)));
                temp =  br.readLine().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                drinksArrays.add(new ArrayList<String>(Arrays.asList(temp)));
                temp =  br.readLine().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                gamesArrays.add(new ArrayList<String>(Arrays.asList(temp)));
                temp =  br.readLine().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                decorationsArrays.add(new ArrayList<String>(Arrays.asList(temp)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        entries = data.size()-1;
        currentEntry = 0;

        lineOfData =  data.get(currentEntry).split(",");
        numOfGuests = lineOfData[0];
        budget = lineOfData[1];
        where = lineOfData[2];
        when = lineOfData[3];
        desc = lineOfData[4];
        theme = lineOfData[5];


        if(budget.equals("")){
            budgetText.setText("");
        }else{
            budgetText.setText("Budget: $" + budget);
        }

        if(!when.equals("")){
            partyPlanText.setText("Party plan for your event on the " + when);
        }else{
            partyPlanText.setText("Party plan for your event");
        }

        if(!numOfGuests.equals("")){
            partyPlanText.setText(partyPlanText.getText().toString() + " for "+numOfGuests+" guests");
        }

        if(!where.equals("")){
            partyPlanText.setText(partyPlanText.getText().toString() + " at "+where);
        }

        if(theme.equals("None")|| theme.equals("")){
            themeText.setText("");
        }else{
            themeText.setText("The theme is "+theme);
        }

        final ArrayList<TextView> foodList = new ArrayList<>();
        ArrayList<String> food = foodArrays.get(currentEntry);

        for(int i = 0; i< food.size();i++){
            foodList.add(new TextView(this));
            foodList.get(i).setText(food.get(i));
            foodList.get(i).setGravity(Gravity.CENTER);
            foodView.addView(foodList.get(i));
        }

        ArrayList<TextView> drinkList = new ArrayList<>();
        ArrayList<String> drinks = drinksArrays.get(currentEntry);


        for(int i = 0; i< drinks.size();i++){
            drinkList.add(new TextView(this));
            drinkList.get(i).setText(drinks.get(i));
            drinkList.get(i).setGravity(Gravity.CENTER);
            drinkView.addView(drinkList.get(i));
        }

        ArrayList<TextView> decorationList = new ArrayList<>();
        ArrayList<String> decoration = decorationsArrays.get(currentEntry);

        for(int i = 0; i< decoration.size();i++){
            decorationList.add(new TextView(this));
            decorationList.get(i).setText(decoration.get(i));
            decorationList.get(i).setGravity(Gravity.CENTER);
            decorationsView.addView(decorationList.get(i));
        }

        ArrayList<TextView> gamesList = new ArrayList<>();
        ArrayList<String> games = decorationsArrays.get(currentEntry);

        for(int i = 0; i< games.size();i++){
            gamesList.add(new TextView(this));
            gamesList.get(i).setText(games.get(i));
            gamesList.get(i).setGravity(Gravity.CENTER);
            gameView.addView(gamesList.get(i));
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entries == 0){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Only one event was found", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    if(currentEntry == entries){
                        currentEntry = 0;
                    }else{
                        currentEntry++;
                    }
                    foodView.removeAllViews();
                    drinkView.removeAllViews();
                    gameView.removeAllViews();
                    decorationsView.removeAllViews();
                    lineOfData =  data.get(currentEntry).split(",");
                    numOfGuests = lineOfData[0];
                    budget = lineOfData[1];
                    where = lineOfData[2];
                    when = lineOfData[3];
                    desc = lineOfData[4];
                    theme = lineOfData[5];


                    if(budget.equals("")){
                        budgetText.setText("");
                    }else{
                        budgetText.setText("Budget: $" + budget);
                    }

                    if(!when.equals("")){
                        partyPlanText.setText("Party plan for your event on the " + when);
                    }else{
                        partyPlanText.setText("Party plan for your event");
                    }

                    if(!numOfGuests.equals("")){
                        partyPlanText.setText(partyPlanText.getText().toString() + " for "+numOfGuests+" guests");
                    }

                    if(!where.equals("")){
                        partyPlanText.setText(partyPlanText.getText().toString() + " at "+where);
                    }

                    if(theme.equals("None")|| theme.equals("")){
                        themeText.setText("");
                    }else{
                        themeText.setText("The theme is "+theme);
                    }


                    ArrayList<TextView> foodList = new ArrayList<>();
                    ArrayList<String> food = foodArrays.get(currentEntry);

                    for(int i = 0; i< food.size();i++){
                        foodList.add(new TextView(getApplicationContext()));
                        foodList.get(i).setText(food.get(i));
                        foodList.get(i).setGravity(Gravity.CENTER);
                        foodView.addView(foodList.get(i));
                    }

                    ArrayList<TextView> drinkList = new ArrayList<>();
                    ArrayList<String> drinks = drinksArrays.get(currentEntry);


                    for(int i = 0; i< drinks.size();i++){
                        drinkList.add(new TextView(getApplicationContext()));
                        drinkList.get(i).setText(drinks.get(i));
                        drinkList.get(i).setGravity(Gravity.CENTER);
                        drinkView.addView(drinkList.get(i));
                    }

                    ArrayList<TextView> decorationList = new ArrayList<>();
                    ArrayList<String> decoration = decorationsArrays.get(currentEntry);

                    for(int i = 0; i< decoration.size();i++){
                        decorationList.add(new TextView(getApplicationContext()));
                        decorationList.get(i).setText(decoration.get(i));
                        decorationList.get(i).setGravity(Gravity.CENTER);
                        decorationsView.addView(decorationList.get(i));
                    }

                    ArrayList<TextView> gamesList = new ArrayList<>();
                    ArrayList<String> games = decorationsArrays.get(currentEntry);

                    for(int i = 0; i< games.size();i++){
                        gamesList.add(new TextView(getApplicationContext()));
                        gamesList.get(i).setText(games.get(i));
                        gamesList.get(i).setGravity(Gravity.CENTER);
                        gameView.addView(gamesList.get(i));
                    }
                }
            }
        });


        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entries == 0){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Only one event was found", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    if(currentEntry == 0){
                        currentEntry = entries;
                    }else{
                        currentEntry--;
                    }
                    foodView.removeAllViews();
                    drinkView.removeAllViews();
                    gameView.removeAllViews();
                    decorationsView.removeAllViews();
                    lineOfData =  data.get(currentEntry).split(",");
                    numOfGuests = lineOfData[0];
                    budget = lineOfData[1];
                    where = lineOfData[2];
                    when = lineOfData[3];
                    desc = lineOfData[4];
                    theme = lineOfData[5];


                    if(budget.equals("")){
                        budgetText.setText("");
                    }else{
                        budgetText.setText("Budget: $" + budget);
                    }

                    if(!when.equals("")){
                        partyPlanText.setText("Party plan for your event on the " + when);
                    }else{
                        partyPlanText.setText("Party plan for your event");
                    }

                    if(!numOfGuests.equals("")){
                        partyPlanText.setText(partyPlanText.getText().toString() + " for "+numOfGuests+" guests");
                    }

                    if(!where.equals("")){
                        partyPlanText.setText(partyPlanText.getText().toString() + " at "+where);
                    }

                    if(theme.equals("None")|| theme.equals("")){
                        themeText.setText("");
                    }else{
                        themeText.setText("The theme is "+theme);
                    }

                    ArrayList<TextView> foodList = new ArrayList<>();
                    ArrayList<String> food = foodArrays.get(currentEntry);

                    for(int i = 0; i< food.size();i++){
                        foodList.add(new TextView(getApplicationContext()));
                        foodList.get(i).setText(food.get(i));
                        foodList.get(i).setGravity(Gravity.CENTER);
                        foodView.addView(foodList.get(i));
                    }

                    ArrayList<TextView> drinkList = new ArrayList<>();
                    ArrayList<String> drinks = drinksArrays.get(currentEntry);


                    for(int i = 0; i< drinks.size();i++){
                        drinkList.add(new TextView(getApplicationContext()));
                        drinkList.get(i).setText(drinks.get(i));
                        drinkList.get(i).setGravity(Gravity.CENTER);
                        drinkView.addView(drinkList.get(i));
                    }

                    ArrayList<TextView> decorationList = new ArrayList<>();
                    ArrayList<String> decoration = decorationsArrays.get(currentEntry);

                    for(int i = 0; i< decoration.size();i++){
                        decorationList.add(new TextView(getApplicationContext()));
                        decorationList.get(i).setText(decoration.get(i));
                        decorationList.get(i).setGravity(Gravity.CENTER);
                        decorationsView.addView(decorationList.get(i));
                    }

                    ArrayList<TextView> gamesList = new ArrayList<>();
                    ArrayList<String> games = decorationsArrays.get(currentEntry);

                    for(int i = 0; i< games.size();i++){
                        gamesList.add(new TextView(getApplicationContext()));
                        gamesList.get(i).setText(games.get(i));
                        gamesList.get(i).setGravity(Gravity.CENTER);
                        gameView.addView(gamesList.get(i));
                    }
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodView.removeAllViews();
                drinkView.removeAllViews();
                gameView.removeAllViews();
                decorationsView.removeAllViews();
                finish();
                startActivity(goBack);
            }
        });

        shareButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,desc);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }

        });
    }
}
