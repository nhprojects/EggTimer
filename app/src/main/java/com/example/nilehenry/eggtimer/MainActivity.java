package com.example.nilehenry.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import org.w3c.dom.Text;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    //public boolean timePaused=true;
    final Clocker clockTime=new Clocker(0,0,0);
    public SeekBar seekBar;//(
    public TextView trackerTextView;
    public CountDownTimer clockTimer;
    public boolean isRunning;
    public Button button;
    MediaPlayer mplayer;

    public void setClockTimer()
    {
        clockTimer=new CountDownTimer((clockTime.getTotalSeconds()+1)*1000,1000) {
            @Override
            public void onTick(long l) {
                isRunning=true;
                clockTime.tickDown();
                Log.i("currentTime",clockTime.toString() + " " + Boolean.toString(isRunning));
                trackerTextView.setText(clockTime.toString());
                button.setText("Pause");
            }

            @Override
            public void onFinish() {
                isRunning=false;
                button.setText("Go");
                Animation anim= new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(200);
                anim.setStartOffset(10);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(10);
                trackerTextView.startAnimation(anim);
                Toast.makeText(MainActivity.this,"Time Done",Toast.LENGTH_SHORT).show();
                mplayer.start();
            }
        };


    }
    public void startCountDown(View view) {
        if (isRunning){
            clockTimer.cancel();
            isRunning=false;
            button.setText("Go");
        }
        else{
            setClockTimer();
            clockTimer.start();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(720);
        seekBar.setProgress(0);
        ImageView eggImageView= (ImageView) findViewById(R.id.eggImageView);

        trackerTextView= (TextView) findViewById(R.id.trackerTextView);
        trackerTextView.setText(clockTime.toString());

        isRunning=false;

         mplayer= MediaPlayer.create(this,R.raw.airhorn);

        button= (Button) findViewById(R.id.button);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                clockTime.setTime(0,0,5*i);
                if (isRunning){
                    clockTimer.cancel();
                    button.setText("Go");
                }
                setClockTimer();
                trackerTextView.setText(clockTime.toString());

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

