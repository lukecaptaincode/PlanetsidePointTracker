package com.lukecaptaincode.planetsidepointtracker;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
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
    public Timer[] timers;
    TimerTask ApointTaskTR, ApointTaskVS;
    TextView trScoreTXT, vsScoreTXT, ncScoreTXT;
    int trScore, vsScore, ncScore, buttonID_A, buttonID_B, buttonID_C, buttonID_D, TimeCounter[], timeATfinish = 60;


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
        TimeCounter = new int[12];
        for (int i = 0; i > TimeCounter.length; i++) {
            TimeCounter[i] = 0;
        }
        isTimerRunning = new Boolean[12];
        Arrays.fill(isTimerRunning, Boolean.FALSE);
        timers = new Timer[12];
        for (int i = 0; i > timers.length; i++) {
            timers[i] = new Timer();
        }

        initTextView();
    }

    public void initButtons() {
        //A Point
        pointButtons = new Button[18];
        pointButtons[0] = (Button) findViewById(R.id.main_btn_ApointTR);
        pointButtons[1] = (Button) findViewById(R.id.main_btn_ApointVS);
        pointButtons[2] = (Button) findViewById(R.id.main_btn_ApointNC);
        pointButtons[3] = (Button) findViewById(R.id.main_btn_ApointCLEAR);
        pointButtons[4] = (Button) findViewById(R.id.main_btn_BpointTR);
        pointButtons[5] = (Button) findViewById(R.id.main_btn_BpointVS);
        pointButtons[6] = (Button) findViewById(R.id.main_btn_BpointNC);
        pointButtons[7] = (Button) findViewById(R.id.main_btn_BpointCLEAR);
        pointButtons[8] = (Button) findViewById(R.id.main_btn_CpointTR);
        pointButtons[9] = (Button) findViewById(R.id.main_btn_CpointVS);
        pointButtons[10] = (Button) findViewById(R.id.main_btn_CpointNC);
        pointButtons[11] = (Button) findViewById(R.id.main_btn_CpointCLEAR);
        pointButtons[12] = (Button) findViewById(R.id.main_btn_DpointTR);
        pointButtons[13] = (Button) findViewById(R.id.main_btn_DpointVS);
        pointButtons[14] = (Button) findViewById(R.id.main_btn_DpointNC);
        pointButtons[15] = (Button) findViewById(R.id.main_btn_DpointCLEAR);
        pointButtons[16] = (Button) findViewById(R.id.main_btn_ClearScore);
        pointButtons[17] = (Button) findViewById(R.id.main_btn_StopTimers);
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

        pointButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("BUTTON LISTENER ADDED", "BUTTON LISTENER ADDED");
                startA_TimerNC(view);
            }
        });
        pointButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopA_Timers(view);
            }
        });
        pointButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startB_TimerTR(view);
            }
        });
        pointButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startB_TimerVS(view);
            }
        });
        pointButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startB_TimerNC(view);
            }
        });
        pointButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopB_Timers(view);
            }
        });
        pointButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startC_TimerTR(view);
            }
        });
        pointButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startC_TimerVS(view);
            }
        });
        pointButtons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startC_TimerNC(view);
            }
        });
        pointButtons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopC_Timers(view);
            }
        });
        pointButtons[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startD_TimerTR(view);
            }
        });
        pointButtons[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startD_TimerVS(view);
            }
        });
        pointButtons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startD_TimerNC(view);
            }
        });
        pointButtons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopD_Timers(view);
            }
        });
        pointButtons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearScore();
            }
        });
        pointButtons[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllTimers(view);
            }
        });
    }

    public void initTextView() {
        trScoreTXT = (TextView) findViewById(R.id.main_txt_TRScore);
        vsScoreTXT = (TextView) findViewById(R.id.main_txt_VSScore);
        ncScoreTXT = (TextView) findViewById(R.id.main_txt_NCScore);
    }

    public void stopAllTimers(View view)
    {

        stopA_Timers(view);
        stopB_Timers(view);
        stopC_Timers(view);
        stopD_Timers(view);
    }
    public void stopA_Timers(View view) {
        for (int i = 0; i < 3; i++) {
            if (isTimerRunning[i] == true) {
                isTimerRunning[i] = false;
                TimeCounter[i] = 0;
                timers[i].cancel();
                timers[i].purge();
                pointButtons[i].setEnabled(true);
            }
        }
    }

    public void stopB_Timers(View view) {
        for (int i = 3; i < 6; i++) {
            if (isTimerRunning[i] == true) {
                isTimerRunning[i] = false;
                TimeCounter[i] = 0;
                timers[i].cancel();
                timers[i].purge();

            }
        }
        for (int i = 4; i < 7; i++) {
            pointButtons[i].setEnabled(true);
        }
    }

    public void stopC_Timers(View view) {
        for (int i = 6; i < 9; i++) {
            if (isTimerRunning[i] == true) {
                isTimerRunning[i] = false;
                TimeCounter[i] = 0;
                timers[i].cancel();
                timers[i].purge();

            }

        }
        for(int i = 8; i<11;i++)
        {
            pointButtons[i].setEnabled(true);
        }
    }
    public void stopD_Timers(View view) {
        for (int i = 9; i < 12; i++) {
            if (isTimerRunning[i] == true) {
                isTimerRunning[i] = false;
                TimeCounter[i] = 0;
                timers[i].cancel();
                timers[i].purge();

            }
        }
        for(int i = 12; i<15;i++)
        {
            pointButtons[i].setEnabled(true);
        }
    }
    public void clearScore()
    {
        final DialogInterface.OnClickListener clearScoreYesNo = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        vsScore = 0;
                        ncScore = 0;
                        trScore = 0;
                        vsScoreTXT.setText("VS");
                        ncScoreTXT.setText("NC");
                        trScoreTXT.setText("TR");
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked

                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", clearScoreYesNo)
                .setNegativeButton("No", clearScoreYesNo).show();
    }

    public void ApointTR() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[0].setEnabled(false);
                if (isTimerRunning[1] == true) {
                    pointButtons[1].setEnabled(true);
                    isTimerRunning[1] = false;
                    TimeCounter[1] = 0;
                    timers[1].cancel();
                    timers[1].purge();
                }
                if (isTimerRunning[2] == true) {
                    pointButtons[2].setEnabled(true);
                    isTimerRunning[2] = false;
                    TimeCounter[2] = 0;
                    timers[2].cancel();
                    timers[2].purge();
                }

                if (TimeCounter[0] == timeATfinish) {
                    addPoint_TR();
                    //timers[0].cancel();
                    TimeCounter[0] = 0;
                    ApointTR();
                    return;
                }
                pointButtons[0].setText("" + String.valueOf(TimeCounter[0]));
                TimeCounter[0]++;
            }
        });

    }

    public void ApointVS() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[1].setEnabled(false);
                if (isTimerRunning[0] == true) {
                    pointButtons[0].setEnabled(true);
                    isTimerRunning[0] = false;
                    TimeCounter[0] = 0;
                    timers[0].cancel();
                    timers[0].purge();
                }
                if (isTimerRunning[2] == true) {
                    pointButtons[2].setEnabled(true);
                    isTimerRunning[2] = false;
                    TimeCounter[2] = 0;

                    timers[2].cancel();
                    timers[2].purge();
                }


                if (TimeCounter[1] == timeATfinish) {
                    addPoint_VS();
                    //timers[1].cancel();
                    TimeCounter[1] = 0;
                    ApointVS();

                    return;
                }
                pointButtons[1].setText("" + String.valueOf(TimeCounter[1]));
                TimeCounter[1]++;
            }
        });


    }

    public void ApointNC() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[2].setEnabled(false);
                if (isTimerRunning[1] == true) {
                    pointButtons[1].setEnabled(true);
                    isTimerRunning[1] = false;
                    TimeCounter[1] = 0;
                    timers[1].cancel();
                    timers[1].purge();
                }
                if (isTimerRunning[0] == true) {
                    pointButtons[0].setEnabled(true);
                    isTimerRunning[0] = false;
                    TimeCounter[0] = 0;
                    timers[0].cancel();
                    timers[0].purge();
                }


                if (TimeCounter[2] == timeATfinish) {
                    addPoint_NC();
                    //timers[0].cancel();
                    TimeCounter[2] = 0;
                    ApointNC();
                    return;
                }
                pointButtons[2].setText("" + String.valueOf(TimeCounter[2]));
                TimeCounter[2]++;
            }
        });

    }


    public void BpointTR() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[4].setEnabled(false);
                if (isTimerRunning[4] == true) {
                    pointButtons[5].setEnabled(true);
                    isTimerRunning[4] = false;
                    TimeCounter[4] = 0;
                    timers[4].cancel();
                    timers[4].purge();
                }
                if (isTimerRunning[5] == true) {
                    pointButtons[6].setEnabled(true);
                    isTimerRunning[5] = false;
                    TimeCounter[5] = 0;
                    timers[5].cancel();
                    timers[5].purge();
                }

                if (TimeCounter[3] == timeATfinish) {
                    addPoint_TR();
                    //timers[0].cancel();
                    TimeCounter[3] = 0;
                    BpointTR();
                    return;
                }
                pointButtons[4].setText("" + String.valueOf(TimeCounter[3]));
                TimeCounter[3]++;
            }
        });

    }

    public void BpointVS() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[5].setEnabled(false);
                if (isTimerRunning[3] == true) {
                    pointButtons[4].setEnabled(true);
                    isTimerRunning[3] = false;
                    TimeCounter[3] = 0;
                    timers[3].cancel();
                    timers[3].purge();
                }
                if (isTimerRunning[5] == true) {
                    pointButtons[6].setEnabled(true);
                    isTimerRunning[5] = false;
                    TimeCounter[5] = 0;
                    timers[5].cancel();
                    timers[5].purge();
                }

                if (TimeCounter[4] == timeATfinish) {
                    addPoint_VS();
                    //timers[0].cancel();
                    TimeCounter[4] = 0;
                    BpointVS();
                    return;
                }
                pointButtons[5].setText("" + String.valueOf(TimeCounter[4]));
                TimeCounter[4]++;
            }
        });

    }

    public void BpointNC() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[6].setEnabled(false);
                if (isTimerRunning[3] == true) {
                    pointButtons[4].setEnabled(true);
                    isTimerRunning[3] = false;
                    TimeCounter[3] = 0;
                    timers[3].cancel();
                    timers[3].purge();
                }
                if (isTimerRunning[4] == true) {
                    pointButtons[5].setEnabled(true);
                    isTimerRunning[4] = false;
                    TimeCounter[4] = 0;
                    timers[4].cancel();
                    timers[4].purge();
                }

                if (TimeCounter[5] == timeATfinish) {
                    addPoint_NC();
                    //timers[0].cancel();
                    TimeCounter[5] = 0;
                    BpointNC();
                    return;
                }
                pointButtons[6].setText("" + String.valueOf(TimeCounter[5]));
                TimeCounter[5]++;
            }
        });

    }

    public void CpointTR() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[8].setEnabled(false);
                if (isTimerRunning[7] == true) {
                    pointButtons[9].setEnabled(true);
                    isTimerRunning[7] = false;
                    TimeCounter[7] = 0;
                    timers[7].cancel();
                    timers[7].purge();
                }
                if (isTimerRunning[8] == true) {
                    pointButtons[10].setEnabled(true);
                    isTimerRunning[8] = false;
                    TimeCounter[8] = 0;
                    timers[8].cancel();
                    timers[8].purge();
                }

                if (TimeCounter[6] == timeATfinish) {
                    addPoint_TR();
                    //timers[0].cancel();
                    TimeCounter[6] = 0;
                    CpointTR();
                    return;
                }
                pointButtons[8].setText("" + String.valueOf(TimeCounter[6]));
                TimeCounter[6]++;
            }
        });

    }

    public void CpointVS() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[9].setEnabled(false);
                if (isTimerRunning[6] == true) {
                    pointButtons[8].setEnabled(true);
                    isTimerRunning[6] = false;
                    TimeCounter[6] = 0;
                    timers[6].cancel();
                    timers[6].purge();
                }
                if (isTimerRunning[8] == true) {
                    pointButtons[10].setEnabled(true);
                    isTimerRunning[8] = false;
                    TimeCounter[8] = 0;
                    timers[8].cancel();
                    timers[8].purge();
                }

                if (TimeCounter[7] == timeATfinish) {
                    addPoint_VS();
                    //timers[0].cancel();
                    TimeCounter[7] = 0;
                    CpointVS();
                    return;
                }
                pointButtons[9].setText("" + String.valueOf(TimeCounter[7]));
                TimeCounter[7]++;
            }
        });

    }

    public void CpointNC() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[10].setEnabled(false);
                if (isTimerRunning[6] == true) {
                    pointButtons[8].setEnabled(true);
                    isTimerRunning[6] = false;
                    TimeCounter[6] = 0;
                    timers[6].cancel();
                    timers[6].purge();
                }
                if (isTimerRunning[7] == true) {
                    pointButtons[9].setEnabled(true);
                    isTimerRunning[7] = false;
                    TimeCounter[7] = 0;
                    timers[7].cancel();
                    timers[7].purge();
                }

                if (TimeCounter[8] == timeATfinish) {
                    addPoint_NC();
                    //timers[0].cancel();
                    TimeCounter[8] = 0;
                    CpointNC();
                    return;
                }
                pointButtons[10].setText("" + String.valueOf(TimeCounter[8]));
                TimeCounter[8]++;
            }
        });

    }

    public void DpointTR() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[12].setEnabled(false);
                if (isTimerRunning[10] == true) {
                    pointButtons[13].setEnabled(true);
                    isTimerRunning[10] = false;
                    TimeCounter[10] = 0;
                    timers[10].cancel();
                    timers[10].purge();
                }
                if (isTimerRunning[11] == true) {
                    pointButtons[14].setEnabled(true);
                    isTimerRunning[11] = false;
                    TimeCounter[11] = 0;
                    timers[11].cancel();
                    timers[11].purge();
                }

                if (TimeCounter[9] == timeATfinish) {
                    addPoint_TR();
                    //timers[0].cancel();
                    TimeCounter[9] = 0;
                    DpointTR();
                    return;
                }
                pointButtons[12].setText("" + String.valueOf(TimeCounter[9]));
                TimeCounter[9]++;
            }
        });

    }

    public void DpointVS() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[13].setEnabled(false);
                if (isTimerRunning[9] == true) {
                    pointButtons[12].setEnabled(true);
                    isTimerRunning[9] = false;
                    TimeCounter[9] = 0;
                    timers[9].cancel();
                    timers[9].purge();
                }
                if (isTimerRunning[11] == true) {
                    pointButtons[14].setEnabled(true);
                    isTimerRunning[11] = false;
                    TimeCounter[11] = 0;
                    timers[11].cancel();
                    timers[11].purge();
                }

                if (TimeCounter[10] == timeATfinish) {
                    addPoint_VS();
                    //timers[0].cancel();
                    TimeCounter[10] = 0;
                    DpointVS();
                    return;
                }
                pointButtons[13].setText("" + String.valueOf(TimeCounter[10]));
                TimeCounter[10]++;
            }
        });
    }

    public void DpointNC() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointButtons[14].setEnabled(false);
                if (isTimerRunning[10] == true) {
                    pointButtons[13].setEnabled(true);
                    isTimerRunning[10] = false;
                    TimeCounter[10] = 0;
                    timers[10].cancel();
                    timers[10].purge();
                }
                if (isTimerRunning[9] == true) {
                    pointButtons[12].setEnabled(true);
                    isTimerRunning[9] = false;
                    TimeCounter[9] = 0;
                    timers[9].cancel();
                    timers[9].purge();
                }

                if (TimeCounter[11] == timeATfinish) {
                    addPoint_NC();
                    //timers[0].cancel();
                    TimeCounter[11] = 0;
                    DpointNC();
                    return;
                }
                pointButtons[14].setText("" + String.valueOf(TimeCounter[11]));
                TimeCounter[11]++;
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
        timers[0].schedule(tt, 0, 1000);

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
        timers[1].schedule(tv, 0, 1000);

    }

    public void startA_TimerNC(View view) {
        isTimerRunning[2] = true;
        timers[2] = new Timer();
        TimerTask tn = new TimerTask() {
            @Override
            public void run() {
                ApointNC();
            }
        };
        //   timers[0].cancel();
        timers[2].schedule(tn, 0, 1000);
    }

    public void startB_TimerTR(View view) {
        isTimerRunning[3] = true;
        timers[3] = new Timer();
        TimerTask tt2 = new TimerTask() {
            @Override
            public void run() {
                BpointTR();
            }
        };
        //   timers[0].cancel();
        timers[3].schedule(tt2, 0, 1000);
    }

    public void startB_TimerVS(View view) {
        isTimerRunning[4] = true;
        timers[4] = new Timer();
        TimerTask tv2 = new TimerTask() {
            @Override
            public void run() {
                BpointVS();
            }
        };
        //   timers[0].cancel();
        timers[4].schedule(tv2, 0, 1000);
    }

    public void startB_TimerNC(View view) {
        isTimerRunning[5] = true;
        timers[5] = new Timer();
        TimerTask tn2 = new TimerTask() {
            @Override
            public void run() {
                BpointNC();
            }
        };
        //   timers[0].cancel();
        timers[5].schedule(tn2, 0, 1000);
    }

    public void startC_TimerTR(View view) {
        isTimerRunning[6] = true;
        timers[6] = new Timer();
        TimerTask tt3 = new TimerTask() {
            @Override
            public void run() {
                CpointTR();
            }
        };
        //   timers[0].cancel();
        timers[6].schedule(tt3, 0, 1000);
    }

    public void startC_TimerVS(View view) {
        isTimerRunning[7] = true;
        timers[7] = new Timer();
        TimerTask tv3 = new TimerTask() {
            @Override
            public void run() {
                CpointVS();
            }
        };
        //   timers[0].cancel();
        timers[7].schedule(tv3, 0, 1000);
    }

    public void startC_TimerNC(View view) {
        isTimerRunning[8] = true;
        timers[8] = new Timer();
        TimerTask tn3 = new TimerTask() {
            @Override
            public void run() {
                CpointNC();
            }
        };
        //   timers[0].cancel();
        timers[8].schedule(tn3, 0, 1000);
    }

    public void startD_TimerTR(View view) {
        isTimerRunning[9] = true;
        timers[9] = new Timer();
        TimerTask tt4 = new TimerTask() {
            @Override
            public void run() {
                DpointTR();
            }
        };
        //   timers[0].cancel();
        timers[9].schedule(tt4, 0, 1000);
    }


    public void startD_TimerVS(View view) {
        isTimerRunning[10] = true;
        timers[10] = new Timer();
        TimerTask tv4 = new TimerTask() {
            @Override
            public void run() {
                DpointVS();
            }
        };
        //   timers[0].cancel();
        timers[10].schedule(tv4, 0, 1000);
    }

    public void startD_TimerNC(View view) {
        isTimerRunning[11] = true;
        timers[11] = new Timer();
        TimerTask tn4 = new TimerTask() {
            @Override
            public void run() {
                DpointNC();
            }
        };
        //   timers[0].cancel();
        timers[11].schedule(tn4, 0, 1000);
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
