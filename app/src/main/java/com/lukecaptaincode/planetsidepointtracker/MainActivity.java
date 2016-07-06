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

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button[] pointButtons;
    mainController mC;
   // Timer[] timers;
    Boolean[] isTimerRunning;

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
        for (int i = 0; i > isTimerRunning.length; ++i) {
            isTimerRunning[i] = new Boolean.valueOf(false);
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
            pointButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("BUTTON LISTENER ADDED", "BUTTON LISTENER ADDED");
                    startA_Timer(view);
                }
            });
        }


    }

    public void initTextView() {
        trScoreTXT = (TextView) findViewById(R.id.main_txt_TRScore);
        vsScoreTXT = (TextView) findViewById(R.id.main_txt_VSScore);
        ncScoreTXT = (TextView) findViewById(R.id.main_txt_NCScore);
    }

    public void startA_Timer(final View view) {
        final int id = ((Integer) view.getTag());
        final Timer [] timers = new Timer[pointButtons.length];
        isTimerRunning[id] = true;
        timers[id] = new Timer();
        switch(id){
            case 0:
                if(isTimerRunning[1] ==true) {
                    timers[1].cancel();
                }
                if(isTimerRunning[2] ==true) {
                    timers[2].cancel();
                }

                break;
            case 1:
                if(isTimerRunning[0] ==true) {
                    timers[0].cancel();
                }
                if(isTimerRunning[2] ==true) {
                    timers[2].cancel();
                }
                break;
            case 2:
                if(isTimerRunning[1] ==true) {
                    timers[1].cancel();
                }
                if(isTimerRunning[0] ==true) {
                    timers[0].cancel();
                }
                break;
            case 3:
                if(isTimerRunning[0] ==true) {
                    timers[0].cancel();
                }
                if(isTimerRunning[1] ==true) {
                    timers[1].cancel();
                }
                if(isTimerRunning[2] ==true) {
                    timers[2].cancel();
                }
                break;

        }
        final TimerTask ApointTask = new TimerTask() {
            public void run() {

                runOnUiThread(new Runnable() {
                    public void run() {
                        if (TimeCounter[id] == timeATfinish) {
                            addPoint_TR();
                            timers[id].cancel();
                            TimeCounter[id] =0;
                            startA_Timer(view);
                            return;
                        }
                        pointButtons[id].setText("" + String.valueOf(TimeCounter[id]));
                        TimeCounter[id]++;
                    }
                });
            }
        };
        timers[id].schedule(ApointTask,0,1000);

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
