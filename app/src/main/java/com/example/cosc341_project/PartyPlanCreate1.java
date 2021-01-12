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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class PartyPlanCreate1 extends AppCompatActivity {

    Button backButton;
    Button nextButton;
    EditText theme;
    EditText numOf;
    EditText budget;
    EditText where;
    EditText when;
    EditText desc;
    GifImageView gifFromResource;
    int saveChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partyplan1create);

        final Intent back = new Intent(this,ChooseActivity.class);
        final Intent next = new Intent(this,PartyPlanCreate2.class);
        final Intent home = new Intent(this, MainActivity.class);

        saveChecker = 0;

        backButton = (Button) (findViewById(R.id.backPlan1cButton));
        nextButton = (Button) (findViewById(R.id.nextPlan1cButton));
        theme = (findViewById(R.id.themeCEditText));
        numOf = (findViewById(R.id.numOfcEditText));
        budget = (findViewById(R.id.budgetcEditText));
        where = (findViewById(R.id.wherecEditText));
        when = (findViewById(R.id.whencEditText));
        desc = (findViewById(R.id.desccEditText));
        gifFromResource= (pl.droidsonroids.gif.GifImageView) findViewById(R.id.helpPlan1cButton);
        gifFromResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveChecker < 1) {
                    AlertDialog.Builder suh = new AlertDialog.Builder(PartyPlanCreate1.this, android.R.style.ThemeOverlay_Material_Dark);

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


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(back);
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themeB = theme.getText().toString();
                String numOfB = numOf.getText().toString();
                String budgetB = budget.getText().toString();
                String whereB = where.getText().toString();
                String whenB = when.getText().toString();
                String descB = desc.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("theme",themeB);
                bundle.putString("numOf",numOfB);
                bundle.putString("budget",budgetB);
                bundle.putString("where",whereB);
                bundle.putString("when",whenB);
                bundle.putString("desc",descB);
                if (themeB.length() < 1 || numOfB.length() < 1 || budgetB.length() < 1 ||  whereB.length() < 1 || whenB.length() < 1 || descB.length() < 1) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "You haven't finished planning yet! Please complete all fields to proceed.", Toast.LENGTH_LONG);
                    TextView align = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( align != null) align.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                    next.putExtras(bundle);
                    startActivity(next);
                    finish();
                }


            }
        });


    }
}



