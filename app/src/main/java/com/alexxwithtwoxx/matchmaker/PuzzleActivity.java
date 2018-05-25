package com.alexxwithtwoxx.matchmaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PuzzleActivity extends AppCompatActivity implements View.OnClickListener {

    TextView score_player;

    ArrayList<ImageView> views;
    ImageView iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24, iv_31, iv_32, iv_33, iv_34, iv_41, iv_42, iv_43, iv_44;

    //image array
    Integer[] cardsArray = {101, 102, 103, 104, 201, 202, 203, 204, 301, 302, 303, 304, 401, 402, 403, 404};

    int[] numImages;

    ImageView firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    int turn = 1;
    int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        score_player = findViewById(R.id.textView);

        views = new ArrayList<>();
        views.add((ImageView) findViewById(R.id.iv_11));
        views.add((ImageView) findViewById(R.id.iv_12));
        views.add((ImageView) findViewById(R.id.iv_13));
        views.add((ImageView) findViewById(R.id.iv_14));
        views.add((ImageView) findViewById(R.id.iv_21));
        views.add((ImageView) findViewById(R.id.iv_22));
        views.add((ImageView) findViewById(R.id.iv_23));
        views.add((ImageView) findViewById(R.id.iv_24));
        views.add((ImageView) findViewById(R.id.iv_31));
        views.add((ImageView) findViewById(R.id.iv_32));
        views.add((ImageView) findViewById(R.id.iv_33));
        views.add((ImageView) findViewById(R.id.iv_34));
        views.add((ImageView) findViewById(R.id.iv_41));
        views.add((ImageView) findViewById(R.id.iv_42));
        views.add((ImageView) findViewById(R.id.iv_43));
        views.add((ImageView) findViewById(R.id.iv_44));

        for (int i = 0; i < 8; i++) {
            views.get(i).setTag("" + i);
        }

        for (int i = 8; i <16; i++) {
            views.get(i).setTag("" + (i - 8));
        }

        //load the card images
        numImages = new int[16];
        frontOfCardsResources();

        Collections.shuffle(Arrays.asList(cardsArray));

        for (ImageView v: views) {
            v.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        int theCard = Integer.parseInt((String) view.getTag());
        doStuff((ImageView) view, theCard);
    }

    private void doStuff(ImageView iv, int card){

        iv.setImageResource(numImages[card]);


        //check which image is selected and save it to temporary variable
        if (cardNumber == 1){
            firstCard = iv;

            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);
        }
        else if (cardNumber == 2) {
            secondCard = iv;

            cardNumber = 1;
            clickedSecond = card;

            for (ImageView v : views) {
                v.setEnabled(false);
            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if the selected images are equal
                    calculate();
                }
            }, 1000);
        }
    }

    private void calculate() {

        //if images are equal remove them and add point
        if(firstCard.getTag().equals(secondCard.getTag())) {
            firstCard.setVisibility(View.INVISIBLE);
            secondCard.setVisibility(View.INVISIBLE);

            //add points
            if (turn == 1) {
                playerPoints++;
                score_player.setText("P1: " + playerPoints);
            }
        }
        else
        {
            for (ImageView v : views) {
                v.setImageResource(R.drawable.button_question_mark);
            }
        }

        for (ImageView v : views) {
            v.setEnabled(true);
        }

        //check if round is over
        checkEnd();
    }

    private void checkEnd() {
        for (ImageView v : views) {
            if (v.getVisibility() != View.INVISIBLE) {
                return;
            }
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PuzzleActivity.this);
        alertDialogBuilder
                .setMessage("Game Over!\nP1: " + playerPoints)
                .setCancelable(false)
                .setPositiveButton("New", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), PuzzleActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener()  {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void frontOfCardsResources(){
        numImages[0] = R.drawable.button_1;
        numImages[1] = R.drawable.button_2;
        numImages[2] = R.drawable.button_3;
        numImages[3] = R.drawable.button_4;
        numImages[4] = R.drawable.button_5;
        numImages[5] = R.drawable.button_6;
        numImages[6] = R.drawable.button_7;
        numImages[7] = R.drawable.button_8;
    }
}
