package com.example.cosc341_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class PartyPlanCreate2  extends AppCompatActivity  {

    Button backButton;
    Button nextButton;
    Button addButtonFood;
    Button deleteButtonFood;
    Button addButtonDrink;
    Button deleteButtonDrink;
    Button addButtonGames;
    Button deleteButtonGames;
    Button addButtonDec;
    Button deleteButtonDec;
    ArrayList<TextView> food = new ArrayList<>();
    ArrayList<TextView> drinks= new ArrayList<>();
    ArrayList<TextView> games= new ArrayList<>();
    ArrayList<TextView> decoration= new ArrayList<>();
    EditText foodEditText;
    EditText drinksEditText;
    EditText gamesEditText;
    EditText decorationEditText;
    LinearLayout foodLL;
    LinearLayout drinkLL;
    LinearLayout gamesLL;
    LinearLayout decLL;
    GifImageView gifFromResource;
    int saveChecker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_plan_create2);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();


        final Intent goBack = new Intent(this,PartyPlanCreate1.class);
        final Intent goNext = new Intent(this, CreateFinal.class);
        final Intent home = new Intent(this, MainActivity.class);

        backButton = (Button) (findViewById(R.id.backC2Button));
        saveChecker = 0;
        nextButton = (Button) (findViewById(R.id.next2cButton));
        addButtonFood = (Button) (findViewById(R.id.addFoodbutton));
        deleteButtonFood = (Button) (findViewById(R.id.delFoodbutton2));
        addButtonDrink = (Button) (findViewById(R.id.addDrinkbutton3));
        deleteButtonDrink = (Button) (findViewById(R.id.delDrinkbutton4));
        addButtonGames = (Button) (findViewById(R.id.addGamesbutton7));
        deleteButtonGames = (Button) (findViewById(R.id.delGamesbutton8));
        addButtonDec = (Button) (findViewById(R.id.addDecbutton5));
        deleteButtonDec = (Button) (findViewById(R.id.delDecbutton6));
        foodEditText = (findViewById(R.id.foodCreateEditText));
        drinksEditText = (findViewById(R.id.drinkCEditText));
        gamesEditText = (findViewById(R.id.gamesCreateEditText));
        decorationEditText = (findViewById(R.id.decorationCEditText));
        foodLL = (findViewById(R.id.foodCScrollViewLL));
        drinkLL = (findViewById(R.id.drinkcLL));
        gamesLL = (findViewById(R.id.gamesc2LL));
        decLL = (findViewById(R.id.decCLL));
        gifFromResource= (pl.droidsonroids.gif.GifImageView) findViewById(R.id.push_button4);
        gifFromResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveChecker < 1) {
                    AlertDialog.Builder suh = new AlertDialog.Builder(PartyPlanCreate2.this, android.R.style.ThemeOverlay_Material_Dark);

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





        addButtonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String added = foodEditText.getText().toString();


               if(added.equals("")){
                   Context context = getApplicationContext();
                   Toast toast = Toast.makeText(context, "Unable to add. Empty Field", Toast.LENGTH_SHORT);
                   toast.show();
               }else{
                    foodLL.removeAllViews();
                    TextView newView = new TextView(getApplicationContext());
                    newView.setText(added);
                    food.add(newView);
                    for(TextView item: food){
                        foodLL.addView(item);
                    }
               }
            }
        });

        addButtonDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String added = drinksEditText.getText().toString();


                if(added.equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Unable to add. Empty Field", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    drinkLL.removeAllViews();
                    TextView newView = new TextView(getApplicationContext());
                    newView.setText(added);
                    drinks.add(newView);
                    for(TextView item: drinks){
                        drinkLL.addView(item);
                    }
                }
            }
        });

        addButtonGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String added = gamesEditText.getText().toString();


                if(added.equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Unable to add. Empty Field", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    gamesLL.removeAllViews();
                    TextView newView = new TextView(getApplicationContext());
                    newView.setText(added);
                    games.add(newView);
                    for(TextView item: games){
                        gamesLL.addView(item);
                    }
                }
            }
        });

        addButtonDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String added = decorationEditText.getText().toString();


                if(added.equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Unable to add. Empty Field", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    decLL.removeAllViews();
                    TextView newView = new TextView(getApplicationContext());
                    newView.setText(added);
                    decoration.add(newView);
                    for(TextView item: decoration){
                        decLL.addView(item);
                    }
                }
            }
        });

        deleteButtonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleted = foodEditText.getText().toString();


                if(deleted.equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Unable to delete. Empty Field", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    foodLL.removeAllViews();
                    int indexTodelete = -1;
                    for(int i = 0; i < food.size();i++){
                        if(food.get(i).getText().toString().equals(deleted)){
                            indexTodelete = i;
                        }else{
                            foodLL.addView(food.get(i));
                        }
                    }
                    if(indexTodelete > -1){
                        food.remove(indexTodelete);
                    }else{
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, "Unable to delete. Item does not exist", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        deleteButtonDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleted = drinksEditText.getText().toString();


                if(deleted.equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Unable to delete. Empty Field", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    drinkLL.removeAllViews();
                    int indexTodelete = -1;
                    for(int i = 0; i < drinks.size();i++){
                        if(drinks.get(i).getText().toString().equals(deleted)){
                            indexTodelete = i;
                        }else{
                            drinkLL.addView(drinks.get(i));
                        }
                    }
                    if(indexTodelete > -1){
                        drinks.remove(indexTodelete);
                    }else{
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, "Unable to delete. Item does not exist", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        deleteButtonGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleted = gamesEditText.getText().toString();


                if(deleted.equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Unable to delete. Empty Field", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    gamesLL.removeAllViews();
                    int indexTodelete = -1;
                    for(int i = 0; i < games.size();i++){
                        if(games.get(i).getText().toString().equals(deleted)){
                            indexTodelete = i;
                        }else{
                            gamesLL.addView(games.get(i));
                        }
                    }
                    if(indexTodelete > -1){
                        games.remove(indexTodelete);
                    }else{
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, "Unable to delete. Item does not exist", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        deleteButtonDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleted = decorationEditText.getText().toString();


                if(deleted.equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Unable to delete. Empty Field", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    decLL.removeAllViews();
                    int indexTodelete = -1;
                    for(int i = 0; i < decoration.size();i++){
                        if(decoration.get(i).getText().toString().equals(deleted)){
                            indexTodelete = i;
                        }else{
                            decLL.addView(decoration.get(i));
                        }
                    }
                    if(indexTodelete > -1){
                        decoration.remove(indexTodelete);
                    }else{
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, "Unable to delete. Item does not exist", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goBack);
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> foodList = new ArrayList<>();
                ArrayList<String> drinkList = new ArrayList<>();
                ArrayList<String> gamesList = new ArrayList<>();
                ArrayList<String> decList = new ArrayList<>();

                for(TextView item: food){
                    foodList.add(item.getText().toString());
                }

                for(TextView item: drinks){
                    drinkList.add(item.getText().toString());
                }

                for(TextView item: games){
                    gamesList.add(item.getText().toString());
                }

                for(TextView item: decoration){
                    decList.add(item.getText().toString());
                }

                bundle.putStringArrayList("food",foodList);
                bundle.putStringArrayList("drinks",drinkList);
                bundle.putStringArrayList("games",gamesList);
                bundle.putStringArrayList("decoration",decList);
                goNext.putExtras(bundle);
                startActivity(goNext);
                finish();
            }
        });

    }
}
