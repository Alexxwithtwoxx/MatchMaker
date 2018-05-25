package com.alexxwithtwoxx.matchmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    protected ImageButton buttonPlay;

    protected ImageButton buttonHowTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        buttonPlay = findViewById(R.id.buttonPlay);
        buttonHowTo = findViewById(R.id.buttonHowTo);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PuzzleActivity.class);
                startActivity(intent);


            }
        });
        buttonHowTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HowToPlay.class);
                startActivity(intent);


            }
        });

    }
}
