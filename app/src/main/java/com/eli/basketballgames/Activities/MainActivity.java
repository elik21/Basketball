package com.eli.basketballgames.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eli.basketballgames.R;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText scoreH, scoreG, teamH, teamG, foulsH, foulsG, clock;
    Button minG,incG,minH,incH;
    CountDownTimer countDownTimer;
    Button stopbtn;
    Button resume;
    Button restart;
    Button returnToStart;
    int zH=0,zG=0;
   long time=10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //setTitle(Html.fromHtml("<font color='#50F106'>Basketball Games </font>"));

        try {
            init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //clock();
        stop();
       returnToStart();
       restart();
       resume();
increment();
decrement();



    }
    public void increment(){
        incH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zH++;
                scoreH.setText(""+zH);
            }
        });
        incG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zG++;
                scoreG.setText(""+zG);
            }
        });

    }
    public void decrement(){
        minH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zH--;
                scoreH.setText(""+zH);
            }
        });
        minG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zG--;
                scoreG.setText(""+zG);
            }
        });

    }
    public void returnToStart(){
        returnToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock.setText("10:00:00");
            }
        });
    }
    public void restart(){
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time=600000;
                clock();
            }
        });
    }
    public void resume(){
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock();
            }
        });
    }

    public void init() throws InterruptedException {
        scoreH= findViewById(R.id.scoreH);
        scoreG= findViewById(R.id.scoreG);
        teamH=  findViewById(R.id.teamH);
        teamG = findViewById(R.id.teamG);
        foulsH =findViewById(R.id.foulsG);
        foulsG= findViewById(R.id.foulsH);
        incH=findViewById(R.id.incH);
        minH=findViewById(R.id.minH);
        incG=findViewById(R.id.incG);
        minG=findViewById(R.id.minG);
        clock= findViewById(R.id.clock);
        stopbtn=findViewById(R.id.stopb);
        resume=findViewById(R.id.resume);
        restart=findViewById(R.id.restart);
        returnToStart=findViewById(R.id.newQ);
    }
    public void clock(){

        countDownTimer=new CountDownTimer(time, (long) 10) {
            long milis = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                time=millisUntilFinished;
                clock.setText(""+String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                        (millisUntilFinished%1000)/10
                ));

            }
            @Override
            public void onFinish() {
                clock.setText("00:00:00");

            }

        }.start();
    }
    public void stop(){
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
            }
        });

    }


}