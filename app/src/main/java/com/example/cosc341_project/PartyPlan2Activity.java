package com.example.cosc341_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class PartyPlan2Activity extends AppCompatActivity {

    TextView suggestedTheme;
    Spinner chooseTheme;
    Button backButton;
    Button nextButton;
    GifImageView gifImageView;
    int saveChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_plan2);

        final Intent goBack = new Intent(this,PartyPlan1Activity.class);
        final Intent partyTemplate = new Intent(this,PartyPlanTemplateActivity.class);
        final Intent home = new Intent(this, MainActivity.class);


        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        saveChecker = 0;
        backButton = (Button) (findViewById(R.id.backThemebutton));
        nextButton = (Button) (findViewById(R.id.nextThemeButton));
        chooseTheme = (Spinner) findViewById(R.id.chooseThemeSpinner);
        suggestedTheme = (findViewById(R.id.suggestedThemeTextView));
        gifImageView = (pl.droidsonroids.gif.GifImageView) findViewById(R.id.push_button3);
        gifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveChecker == 0) {
                    AlertDialog.Builder suh = new AlertDialog.Builder(PartyPlan2Activity.this, android.R.style.ThemeOverlay_Material_Dark);

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

        if(Math.random() < 0.5) {
            suggestedTheme.setText("Hawaiian");
        }else{
            suggestedTheme.setText("Retro");
        }

        gifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(home);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(goBack);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theme = chooseTheme.getSelectedItem().toString();
                bundle.putString("theme",theme);
                partyTemplate.putExtras(bundle);
                startActivity(partyTemplate);
                finish();
            }
        });






    }
}
