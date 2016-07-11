package com.lukecaptaincode.planetsidepointtracker;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button[] pointButtons;
    mainController mC;
    Boolean[] isTimerRunning;
    public Timer [] timers;
    TimerTask ApointTaskTR, ApointTaskVS;
    TextView trScoreTXT, vsScoreTXT, ncScoreTXT;
    int trScore, vsScore, ncScore, buttonID_A, buttonID_B, buttonID_C, buttonID_D, TimeCounter[], timeATfinish =60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mC = new mainController();
        trScore = 0;
        vsScore = 0;
        ncScore = 0;
        buttonID_A = 0;
        buttonID_B = 0;
        buttonID_C = 0;
        buttonID_D = 0;
        Log.i("CREATED", "CREATED");
        initButtons();
        TimeCounter = new int[pointButtons.length];
        for(int i=0; i> TimeCounter.length;i++)
        {
            TimeCounter[i] = 0;
        }
        isTimerRunning = new Boolean[pointButtons.length];
        Arrays.fill(isTimerRunning, Boolean.FALSE);
        timers = new Timer[pointButtons.length];
        for(int i = 0; i>timers.length;i++)
        {
            timers[i] = new Timer();
        }

        initTextView();
    }

    public void initButtons() {
        //A Point
        pointButtons = new Button[4];
        pointButtons[0] = (Button) findViewById(R.id.main_btn_ApointTR);
        pointButtons[1] = (Button) findViewById(R.id.main_btn_ApointVS);
        pointButtons[2] = (Button) findViewById(R.id.main_btn_ApointNC);
        pointButtons[3] = (Button) findViewById(R.id.main_btn_ApointCLEAR);
        Log.i("BUTTONS INT", "BUTTONS INIT");

        for (int i = 0; i < pointButtons.length; i++) {
            Log.i("Listener loop Buttons", "Listener add loop Buttons");
            pointButtons[i].setTag(i);

        }
        pointButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("BUTTON LISTENER ADDED", "BUTTON LISTENER ADDED");
                startA_TimerTR(view);
            }
        });
        pointButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("BUTTON LISTENER ADDED", "BUTTON LISTENER ADDED");
                startA_TimerVS(view);
            }
        });

    }

    public void initTextView() {
        trScoreTXT = (TextView) findViewById(R.id.main_txt_TRScore);
        vsScoreTXT = (TextView) findViewById(R.id.main_txt_VSScore);
        ncScoreTXT = (TextView) findViewById(R.id.main_txt_NCScore);
    }
    public  void ApointTR()
    {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isTimerRunning[1] == true) {
                    isTimerRunning[1] = false;
                    timers[1].cancel();
                    timers[1].purge();
                }

                if (TimeCounter[0] == timeATfinish) {
                    addPoint_TR();
                    timers[0].cancel();
                    TimeCounter[0] =0;

                    return;
                }
                pointButtons[0].setText("" + String.valueOf(TimeCounter[0]));
                TimeCounter[0]++;
            }
        });

    }
    public void ApointVS()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isTimerRunning[0] == true) {
                    isTimerRunning[0] = false;
                    timers[0].cancel();
                    timers[0].purge();
                }


                if (TimeCounter[1] == timeATfinish) {
                    addPoint_VS();
                    timers[1].cancel();
                    TimeCounter[1] =0;

                    return;
                }
                pointButtons[1].setText("" + String.valueOf(TimeCounter[1]));
                TimeCounter[1]++;
            }
        });


    }

    public void startA_TimerTR(View view) {

        isTimerRunning[0] = true;
        timers[0] = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                ApointTR();
            }
        };
        timers[0].schedule(tt,0,1000);

    }
    public void startA_TimerVS(final View view) {
        isTimerRunning[1] = true;
        timers[1] = new Timer();
        TimerTask tv = new TimerTask() {
            @Override
            public void run() {
                ApointVS();
            }
        };
     //   timers[0].cancel();
        timers[1].schedule(tv,0,1000);

    }



    public void addPoint_TR() {
        trScore = trScore + 1;
        trScoreTXT.setText("TR " + Integer.toString(trScore));
    }

    public void addPoint_VS() {
        vsScore = vsScore + 1;
        vsScoreTXT.setText("VS " + Integer.toString(vsScore));
    }

    public void addPoint_NC() {
        ncScore = ncScore + 1;
        ncScoreTXT.setText("NC " + Integer.toString(ncScore));
    }

}
