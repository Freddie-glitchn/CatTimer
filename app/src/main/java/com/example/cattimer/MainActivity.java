package com.example.cattimer;



import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    SeekBar seekBar;
    TextView timerView;
    Boolean counterIsActive = false;
    Button controllerButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        timerView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Start");
        seekBar.setEnabled(true);
        counterIsActive = false;

    }
    public void updateTimer(int secondsLeft){

        int mins = (int) secondsLeft / 60;
        int sec = secondsLeft - mins * 60;

        String secondString = Integer.toString(sec);

        if (sec <= 9){

            secondString = "0" + secondString;

        }

        timerView.setText(Integer.toString(mins) + " : " + secondString);

    }

    public void controlTimer(View view){

        if (counterIsActive == false) {
            counterIsActive = true;
            seekBar.setEnabled(false);
            controllerButton.setText("Stop");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {

                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.sos);
                    mplayer.start();

                }
            }.start();
        }else {

            resetTimer();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        timerView = (TextView) findViewById(R.id.timerView);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
