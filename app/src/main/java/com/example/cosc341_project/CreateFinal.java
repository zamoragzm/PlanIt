package com.example.cosc341_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import pl.droidsonroids.gif.GifImageView;

public class CreateFinal extends AppCompatActivity {

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
    ArrayList<String> food;
    ArrayList<String> drinks;
    ArrayList<String> games;
    ArrayList<String> decoration;
    String theme;
    int saveChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createfinal);

        final Intent goBack = new Intent(this,PartyPlanCreate2.class);
        final Intent home = new Intent(this, MainActivity.class);
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        String partyType = bundle.getString("partyType");
        final String numOfGuests = bundle.getString("numOf");
        final String budget = bundle.getString("budget");
        final String where = bundle.getString("where");
        final String when = bundle.getString("when");
        final String desc = bundle.getString("desc");
        saveChecker = 0;
        theme = bundle.getString("theme");
        food =  bundle.getStringArrayList("food");
        drinks =  bundle.getStringArrayList("drinks");
        games =  bundle.getStringArrayList("games");
        decoration = bundle.getStringArrayList("decoration");


        backButton = (Button) (findViewById(R.id.backC2Button));
        doneButton = (pl.droidsonroids.gif.GifImageView) (findViewById(R.id.push_button4));
        saveButton = (Button) (findViewById(R.id.savePlanCFButton));
        shareButton = (Button) (findViewById(R.id.shareButton));
        foodView = (findViewById(R.id.foodCScrollViewLL));
        drinkView = (findViewById(R.id.drinkcLL));
        gameView = (findViewById(R.id.gamesc2LL));
        decorationsView = (findViewById(R.id.decCLL));
        partyPlanText = (findViewById(R.id.partyPlanTempTextView));
        themeText = (findViewById(R.id.partyThemeTempTextView));
        budgetText = (findViewById(R.id.budgetTempTextView));

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

        if(theme.equals("")){
            themeText.setText("");
            theme = ("None");
        }else{
            themeText.setText("The theme is "+theme);
        }

        ArrayList<TextView> foodList = new ArrayList<>();

        for(int i = 0; i< food.size();i++){
            foodList.add(new TextView(this));
            foodList.get(i).setText(food.get(i));
            foodList.get(i).setGravity(Gravity.CENTER);
            foodView.addView(foodList.get(i));
        }

        ArrayList<TextView> drinkList = new ArrayList<>();

        for(int i = 0; i< drinks.size();i++){
            drinkList.add(new TextView(this));
            drinkList.get(i).setText(drinks.get(i));
            drinkList.get(i).setGravity(Gravity.CENTER);
            drinkView.addView(drinkList.get(i));
        }

        ArrayList<TextView> decorationList = new ArrayList<>();

        for(int i = 0; i< decoration.size();i++){
            decorationList.add(new TextView(this));
            decorationList.get(i).setText(decoration.get(i));
            decorationList.get(i).setGravity(Gravity.CENTER);
            decorationsView.addView(decorationList.get(i));
        }

        ArrayList<TextView> gamesList = new ArrayList<>();

        for(int i = 0; i< games.size();i++){
            gamesList.add(new TextView(this));
            gamesList.get(i).setText(games.get(i));
            gamesList.get(i).setGravity(Gravity.CENTER);
            gameView.addView(gamesList.get(i));
        }

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveChecker < 1) {
                    AlertDialog.Builder suh = new AlertDialog.Builder(CreateFinal.this, android.R.style.ThemeOverlay_Material_Dark);

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
                String foodContents = Arrays.toString(food.toArray())+"\n";
                String drinkContents = Arrays.toString(drinks.toArray())+"\n";
                String gameContents = Arrays.toString(games.toArray())+"\n";
                String decorationContents = Arrays.toString(decoration.toArray())+"\n";
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
                    Toast toast = Toast.makeText(context, "Plan saved to " + path, Toast.LENGTH_LONG);
                    toast.show();
                    saveChecker++;
                    finish();

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
