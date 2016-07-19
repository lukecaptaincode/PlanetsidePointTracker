package com.lukecaptaincode.planetsidepointtracker;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Planetside Point Tracker
 * Created by Luke Captain on 03/07/2016.
 * For information contact
 * lukecaptaincode@gmail.com
 */
public class mainModel {

    MainActivity mainActivity;
    Boolean[] isTimerRunning;
    Button [] pointButtons, neutralButtons;
    Button clearScores;
    public Timer[] timers;
    TextView trScoreTXT, vsScoreTXT, ncScoreTXT;
    int trScore, vsScore, ncScore, TimeCounter[], timeATfinish = 60;

    public mainModel(Button [] pointButts,TextView trTXT, TextView vsTXT, TextView ncTXT)
    {
        mainActivity = new MainActivity();
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

        pointButtons = pointButts;
        trScoreTXT = trTXT;
        vsScoreTXT = vsTXT;
        ncScoreTXT = ncTXT;
    }

    public void stopAllTimers(View view) {
        stop_TimersA(view);
        stop_TimersB(view);
        stop_TimersC(view);
        stop_TimersD(view);
    }

    public void stop_TimersA(View view) {
        for (int i = 0; i < 3; i++) {
            if (isTimerRunning[i]) {
                isTimerRunning[i] = false;
                TimeCounter[i] = 0;
                timers[i].cancel();
                timers[i].purge();
                pointButtons[i].setEnabled(true);
            }
        }
    }

    public void stop_TimersB(View view) {
        for (int i = 3; i < 6; i++) {
            if (isTimerRunning[i]) {
                isTimerRunning[i] = false;
                TimeCounter[i] = 0;
                timers[i].cancel();
                timers[i].purge();
                pointButtons[i].setEnabled(true);
            }
        }
    }

    public void stop_TimersC(View view) {
        for (int i = 6; i < 9; i++) {
            if (isTimerRunning[i]) {
                isTimerRunning[i] = false;
                TimeCounter[i] = 0;
                timers[i].cancel();
                timers[i].purge();
                pointButtons[i].setEnabled(true);
            }

        }
    }

    public void stop_TimersD(View view) {
        for (int i = 9; i < 12; i++) {
            if (isTimerRunning[i]) {
                isTimerRunning[i] = false;
                TimeCounter[i] = 0;
                timers[i].cancel();
                timers[i].purge();
                pointButtons[i].setEnabled(true);
            }
        }
    }

    public void clearScore() {
        final DialogInterface.OnClickListener clearScoreYesNo = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", clearScoreYesNo)
                .setNegativeButton("No", clearScoreYesNo).show();
    }

    public void startPoint(final int tag, View view) {
        isTimerRunning[tag] = true;
        runningCheck(tag);
        timers[tag] = new Timer();

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                pointTask(tag);
            }
        };
        timers[tag].schedule(tt, 0, 1000);
    }

    public void runningCheck(int tag) {

        switch (tag) {
            case 0:
                pointButtons[0].setEnabled(false);
                if (isTimerRunning[1]) {
                    pointButtons[1].setEnabled(true);
                    isTimerRunning[1] = false;
                    TimeCounter[1] = 0;
                    timers[1].cancel();
                    timers[1].purge();
                }
                if (isTimerRunning[2]) {
                    pointButtons[2].setEnabled(true);
                    isTimerRunning[2] = false;
                    TimeCounter[2] = 0;
                    timers[2].cancel();
                    timers[2].purge();
                }
                break;
            case 1:
                pointButtons[1].setEnabled(false);
                if (isTimerRunning[0]) {
                    pointButtons[0].setEnabled(true);
                    isTimerRunning[0] = false;
                    TimeCounter[0] = 0;
                    timers[0].cancel();
                    timers[0].purge();
                }
                if (isTimerRunning[2]) {
                    pointButtons[2].setEnabled(true);
                    isTimerRunning[2] = false;
                    TimeCounter[2] = 0;

                    timers[2].cancel();
                    timers[2].purge();
                }
                break;
            case 2:
                pointButtons[2].setEnabled(false);
                if (isTimerRunning[1]) {
                    pointButtons[1].setEnabled(true);
                    isTimerRunning[1] = false;
                    TimeCounter[1] = 0;
                    timers[1].cancel();
                    timers[1].purge();
                }
                if (isTimerRunning[0]) {
                    pointButtons[0].setEnabled(true);
                    isTimerRunning[0] = false;
                    TimeCounter[0] = 0;
                    timers[0].cancel();
                    timers[0].purge();
                }
                break;
            case 3:
                pointButtons[3].setEnabled(false);
                if (isTimerRunning[4]) {
                    pointButtons[4].setEnabled(true);
                    isTimerRunning[4] = false;
                    TimeCounter[4] = 0;
                    timers[4].cancel();
                    timers[4].purge();
                }
                if (isTimerRunning[5]) {
                    pointButtons[5].setEnabled(true);
                    isTimerRunning[5] = false;
                    TimeCounter[5] = 0;
                    timers[5].cancel();
                    timers[5].purge();
                }
                break;
            case 4:
                pointButtons[4].setEnabled(false);
                if (isTimerRunning[3]) {
                    pointButtons[3].setEnabled(true);
                    isTimerRunning[3] = false;
                    TimeCounter[3] = 0;
                    timers[3].cancel();
                    timers[3].purge();
                }
                if (isTimerRunning[5]) {
                    pointButtons[5].setEnabled(true);
                    isTimerRunning[5] = false;
                    TimeCounter[5] = 0;
                    timers[5].cancel();
                    timers[5].purge();
                }
                break;
            case 5:
                pointButtons[5].setEnabled(false);
                if (isTimerRunning[3]) {
                    pointButtons[3].setEnabled(true);
                    isTimerRunning[3] = false;
                    TimeCounter[3] = 0;
                    timers[3].cancel();
                    timers[3].purge();
                }
                if (isTimerRunning[4]) {
                    pointButtons[4].setEnabled(true);
                    isTimerRunning[4] = false;
                    TimeCounter[4] = 0;
                    timers[4].cancel();
                    timers[4].purge();
                }
                break;
            case 6:
                pointButtons[6].setEnabled(false);
                if (isTimerRunning[7]) {
                    pointButtons[7].setEnabled(true);
                    isTimerRunning[7] = false;
                    TimeCounter[7] = 0;
                    timers[7].cancel();
                    timers[7].purge();
                }
                if (isTimerRunning[8]) {
                    pointButtons[8].setEnabled(true);
                    isTimerRunning[8] = false;
                    TimeCounter[8] = 0;
                    timers[8].cancel();
                    timers[8].purge();
                }
                break;
            case 7:
                pointButtons[7].setEnabled(false);
                if (isTimerRunning[6]) {
                    pointButtons[6].setEnabled(true);
                    isTimerRunning[6] = false;
                    TimeCounter[6] = 0;
                    timers[6].cancel();
                    timers[6].purge();
                }
                if (isTimerRunning[8]) {
                    pointButtons[8].setEnabled(true);
                    isTimerRunning[8] = false;
                    TimeCounter[8] = 0;
                    timers[8].cancel();
                    timers[8].purge();
                }
                break;
            case 8:
                pointButtons[8].setEnabled(false);
                if (isTimerRunning[6]) {
                    pointButtons[6].setEnabled(true);
                    isTimerRunning[6] = false;
                    TimeCounter[6] = 0;
                    timers[6].cancel();
                    timers[6].purge();
                }
                if (isTimerRunning[7]) {
                    pointButtons[7].setEnabled(true);
                    isTimerRunning[7] = false;
                    TimeCounter[7] = 0;
                    timers[7].cancel();
                    timers[7].purge();
                }
                break;
            case 9:
                pointButtons[9].setEnabled(false);
                if (isTimerRunning[10]) {
                    pointButtons[10].setEnabled(true);
                    isTimerRunning[10] = false;
                    TimeCounter[10] = 0;
                    timers[10].cancel();
                    timers[10].purge();
                }
                if (isTimerRunning[11]) {
                    pointButtons[11].setEnabled(true);
                    isTimerRunning[11] = false;
                    TimeCounter[11] = 0;
                    timers[11].cancel();
                    timers[11].purge();
                }
                break;
            case 10:
                pointButtons[10].setEnabled(false);
                if (isTimerRunning[9]) {
                    pointButtons[9].setEnabled(true);
                    isTimerRunning[9] = false;
                    TimeCounter[9] = 0;
                    timers[9].cancel();
                    timers[9].purge();
                }
                if (isTimerRunning[11]) {
                    pointButtons[11].setEnabled(true);
                    isTimerRunning[11] = false;
                    TimeCounter[11] = 0;
                    timers[11].cancel();
                    timers[11].purge();
                }
                break;
            case 11:
                pointButtons[11].setEnabled(false);
                if (isTimerRunning[10]) {
                    pointButtons[10].setEnabled(true);
                    isTimerRunning[10] = false;
                    TimeCounter[10] = 0;
                    timers[10].cancel();
                    timers[10].purge();
                }
                if (isTimerRunning[9]) {
                    pointButtons[9].setEnabled(true);
                    isTimerRunning[9] = false;
                    TimeCounter[9] = 0;
                    timers[9].cancel();
                    timers[9].purge();
                }
                break;
        }
    }

    public void pointTask(final int mainTag) {

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (TimeCounter[mainTag] == timeATfinish) {
                    addPoint(mainTag);
                    //timers[0].cancel();
                    TimeCounter[mainTag] = 0;
                    pointTask(mainTag);
                    return;
                }
                pointButtons[mainTag].setText("" + String.valueOf(TimeCounter[mainTag]));
                TimeCounter[mainTag]++;
            }
        });

    }

    public void addPoint(final int mainTag) {
        switch (mainTag) {
            case 0:
                addPoint_TR();
                break;
            case 1:
                addPoint_VS();
                break;
            case 2:
                addPoint_NC();
                break;
            case 3:
                addPoint_TR();
                break;
            case 4:
                addPoint_VS();
                break;
            case 5:
                addPoint_NC();
                break;
            case 6:
                addPoint_TR();
                break;
            case 7:
                addPoint_VS();
                break;
            case 8:
                addPoint_NC();
                break;
            case 9:
                addPoint_TR();
                break;
            case 10:
                addPoint_VS();
                break;
            case 11:
                addPoint_NC();
                break;
        }
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
