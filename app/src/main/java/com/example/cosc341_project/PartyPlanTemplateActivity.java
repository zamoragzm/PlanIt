package com.example.cosc341_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import pl.droidsonroids.gif.GifImageView;

public class PartyPlanTemplateActivity extends AppCompatActivity {

    Button backButton;
    GifImageView doneButton;
    Button shareButton;
    Button saveButton;
    LinearLayout foodView;
    LinearLayout drinkView;
    LinearLayout gameView;
    LinearLayout decorationsView;
    TextView partyPlanText;
    TextView themeText;
    TextView budgetText;
    String[] food;
    String[] drinks;
    String[] games;
    String[] decoration;
    int saveChecker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_plan_template);



        final Intent goBack = new Intent(this,PartyPlan2Activity.class);
        final Intent home = new Intent(this, MainActivity.class);
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        final String partyType = bundle.getString("partyType");
        final String numOfGuests = bundle.getString("numOfGuests");
        final String budget = bundle.getString("budget");
        final String where = bundle.getString("where");
        final String when = bundle.getString("when");
        final String desc = bundle.getString("desc");
        final String theme = bundle.getString("theme");

        backButton = (Button) (findViewById(R.id.backC2Button));
        doneButton = (pl.droidsonroids.gif.GifImageView) (findViewById(R.id.push_button4));
        shareButton = (Button)findViewById(R.id.shareButton);
        saveButton = (Button) (findViewById(R.id.savePlanCFButton));
        foodView = (findViewById(R.id.foodCScrollViewLL));
        drinkView = (findViewById(R.id.drinkcLL));
        gameView = (findViewById(R.id.gamesc2LL));
        decorationsView = (findViewById(R.id.decCLL));
        partyPlanText = (findViewById(R.id.partyPlanTempTextView));
        themeText = (findViewById(R.id.partyThemeTempTextView));
        budgetText = (findViewById(R.id.budgetTempTextView));
        saveChecker = 0;

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

        if(theme.equals("None")){
            themeText.setText("");
        }else{
            themeText.setText("The theme is "+theme);
        }


        if(partyType.equals("Work Party")){
            food = getResources().getStringArray(R.array.foodWork);
            drinks = getResources().getStringArray(R.array.drinkWork);
        }else if(partyType.equals("Late-Night Party")){
            food = getResources().getStringArray(R.array.foodParty);
            drinks = getResources().getStringArray(R.array.foodParty);
        }else{
            food = getResources().getStringArray(R.array.foodWork);
            drinks = getResources().getStringArray(R.array.foodParty);
        }

        if(theme.equals("Hawaiian")){
            games = getResources().getStringArray(R.array.gamesHawaiian);
            decoration = getResources().getStringArray(R.array.decorationsHawaiian);
        }else if(partyType.equals("Retro")){
            games = getResources().getStringArray(R.array.gamesRetro);
            decoration = getResources().getStringArray(R.array.decorationsRetro);
        }else{
            decoration = getResources().getStringArray(R.array.undefinedDecorations);
            if(!partyType.equals("Work Party")){
                games = getResources().getStringArray(R.array.undefinedGames);
            }else{
                games = getResources().getStringArray(R.array.gamesRetro);
            }
        }

        final ArrayList<TextView> foodList = new ArrayList<>();

        for(int i = 0; i< food.length;i++){
            foodList.add(new TextView(this));
            foodList.get(i).setText(food[i]);
            foodList.get(i).setGravity(Gravity.CENTER);
            foodView.addView(foodList.get(i));
        }

        ArrayList<TextView> drinkList = new ArrayList<>();

        for(int i = 0; i< drinks.length;i++){
            drinkList.add(new TextView(this));
            drinkList.get(i).setText(drinks[i]);
            drinkList.get(i).setGravity(Gravity.CENTER);
            drinkView.addView(drinkList.get(i));
        }

        ArrayList<TextView> decorationList = new ArrayList<>();

        for(int i = 0; i< decoration.length;i++){
            decorationList.add(new TextView(this));
            decorationList.get(i).setText(decoration[i]);
            decorationList.get(i).setGravity(Gravity.CENTER);
            decorationsView.addView(decorationList.get(i));
        }

        ArrayList<TextView> gamesList = new ArrayList<>();

        for(int i = 0; i< games.length;i++){
            gamesList.add(new TextView(this));
            gamesList.get(i).setText(games[i]);
            gamesList.get(i).setGravity(Gravity.CENTER);
            gameView.addView(gamesList.get(i));
        }




        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveChecker < 1) {
                    AlertDialog.Builder suh = new AlertDialog.Builder(PartyPlanTemplateActivity.this, android.R.style.ThemeOverlay_Material_Dark);

                    suh.setCancelable(false);
                    suh.setTitle("You haven't saved your plan!");
                    suh.setMessage("Are you sure you would like to discard this plan and return to the home page?");
                    suh.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    suh.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(home);
                        }
                    });
                    suh.show();


                } else {
                    startActivity(home);
                }
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "output.txt";
                String fileContents = numOfGuests+","+budget+","+where+","+when+","
                        +desc.trim().replaceAll("\\s","")+","+theme+"\n";
                String foodContents = Arrays.toString(food)+"\n";
                String drinkContents = Arrays.toString(drinks)+"\n";
                String gameContents = Arrays.toString(games)+"\n";
                String decorationContents = Arrays.toString(decoration)+"\n";
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(filename, Context.MODE_APPEND);
                    outputStream.write(fileContents.getBytes());
                    outputStream.write(foodContents.getBytes());
                    outputStream.write(drinkContents.getBytes());
                    outputStream.write(gameContents.getBytes());
                    outputStream.write(decorationContents.getBytes());
                    outputStream.close();
                    Context context = getApplicationContext();
                    String path = context.getFilesDir().getAbsolutePath();
                    Toast toast = Toast.makeText(context, "Plan saved to" + path, Toast.LENGTH_SHORT);
                    saveChecker++;
                    toast.show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                goBack.putExtras(bundle);
                startActivity(goBack);

            }
        });
    }
}
