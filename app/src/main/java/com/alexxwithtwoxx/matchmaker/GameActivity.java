package com.alexxwithtwoxx.matchmaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Canvas;
import android.media.SoundPool

public class GameActivity extends AppCompatActivity {

    Canvas canvas;

    //Sound
    //Initialize Sound Variables
    private SoundPool soundpool;

    int sample1 = -1;
    int sample2 = -1;
    int sample3 = -1;
    int sample4 = -1;

    //game stats
    long lastFrameTime;
    int fps;
    int score;
    int hi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

       // loadSound();

    }





    public void controlFPS() {
            long thisFrameTime = (System.currentTimeMillis() - lastFrameTime);
            long timeToSleep = 15 - thisFrameTime; // 15 milli between frames. wait if running too slow

            if (thisFrameTime > 0){
                fps = (int) (1000 / thisFrameTime);
            }

            if (timeToSleep > 0) {
                try {
                  Thread.sleep(timeToSleep);
                }
                catch (InterruptedException e) {

                }
            }
            lastFrameTime = System.currentTimeMillis();
        }


        public void pause() {


        }

        public void resume(){


        }

}
