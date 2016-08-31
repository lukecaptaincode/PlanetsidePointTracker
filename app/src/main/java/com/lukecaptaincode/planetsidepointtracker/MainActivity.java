package com.lukecaptaincode.planetsidepointtracker;


import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Timer;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity {

    Button[] pointButtons, neutralButtons;
    Button clearScores;
    FloatingActionMenu aM;
    mainController mC;
    Boolean[] isTimerRunning;
    public Timer[] timers;
    mainModel MainModel;
    TextView trScoreTXT, vsScoreTXT, ncScoreTXT;
    int trScore, vsScore, ncScore, TimeCounter[], timeATfinish = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mC = new mainController();
        trScore = 0;
        vsScore = 0;
        ncScore = 0;

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
        MainModel = new mainModel(pointButtons, trScoreTXT, vsScoreTXT, ncScoreTXT);
    }

    public void initButtons() {
        //Floating radial menu
        ImageView iconSettings = new ImageView(this); // Create an icon
        iconSettings.setImageDrawable(getResources().getDrawable(R.drawable.settings_ic));

        FloatingActionButton settingsButton = (FloatingActionButton) findViewById(R.id.main_float_Menu);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView iconTimers = new ImageView(this);
        ImageView iconScores = new ImageView(this);
        iconTimers.setImageDrawable(getResources().getDrawable(R.drawable.timeroff_ic));
        iconScores.setImageDrawable(getResources().getDrawable(R.drawable.score_ic));

        SubActionButton timersSub = itemBuilder.setContentView(iconTimers).build();
        SubActionButton scoreSub = itemBuilder.setContentView(iconScores).build();

       aM = new FloatingActionMenu.Builder(this)
                .addSubActionView(timersSub)
                .addSubActionView(scoreSub)
                .attachTo(settingsButton)
                .build();

        pointButtons = new Button[12];
        pointButtons[0] = (Button) findViewById(R.id.main_btn_ApointTR);
        pointButtons[1] = (Button) findViewById(R.id.main_btn_ApointVS);
        pointButtons[2] = (Button) findViewById(R.id.main_btn_ApointNC);
        pointButtons[3] = (Button) findViewById(R.id.main_btn_BpointTR);
        pointButtons[4] = (Button) findViewById(R.id.main_btn_BpointVS);
        pointButtons[5] = (Button) findViewById(R.id.main_btn_BpointNC);
        pointButtons[6] = (Button) findViewById(R.id.main_btn_CpointTR);
        pointButtons[7] = (Button) findViewById(R.id.main_btn_CpointVS);
        pointButtons[8] = (Button) findViewById(R.id.main_btn_CpointNC);
        pointButtons[9] = (Button) findViewById(R.id.main_btn_DpointTR);
        pointButtons[10] = (Button) findViewById(R.id.main_btn_DpointVS);
        pointButtons[11] = (Button) findViewById(R.id.main_btn_DpointNC);
        neutralButtons = new Button[4];
        neutralButtons[0] = (Button) findViewById(R.id.main_btn_ApointCLEAR);
        neutralButtons[1] = (Button) findViewById(R.id.main_btn_BpointCLEAR);
        neutralButtons[2] = (Button) findViewById(R.id.main_btn_CpointCLEAR);
        neutralButtons[3] = (Button) findViewById(R.id.main_btn_DpointCLEAR);
     //   neutralButtons[4] = (Button) findViewById(R.id.main_btn_StopTimers);
      //  clearScores = (Button) findViewById(R.id.main_btn_ClearScore);

        for (int i =0; i< neutralButtons.length;i++)
        {final int o = i;

            neutralButtons[i].setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View view) {
                   switch (o){
                       case 0:
                           MainModel.stop_TimersA(view);
                           break;
                       case 1:
                           MainModel.stop_TimersB(view);
                           break;
                       case 2:
                           MainModel.stop_TimersC(view);
                           break;
                       case 3:
                           MainModel.stop_TimersD(view);
                           break;
                    //   case 4:
                        //   MainModel.stopAllTimers(view);
                         //  break;
                   }
               }
           });
        }
        timersSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainModel.stopAllTimers(view);
            }
        });
        scoreSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = getApplicationContext();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", clearScoreYesNo)
                        .setNegativeButton("No", clearScoreYesNo).show();
            }
        });
        Log.i("BUTTONS INT", "BUTTONS INIT");

        for (int i = 0; i < pointButtons.length; i++) {
            Log.i("Listener loop Buttons", "Listener add loop Buttons");
            pointButtons[i].setTag(i);
            final int o = i;
            pointButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("BUTTON LISTENER ADDED", "BUTTON LISTENER ADDED");
                    int tag = (Integer) pointButtons[o].getTag();

                    MainModel.startPoint(tag, view);
                }
            });

        }

    }


    public void initTextView() {
        trScoreTXT = (TextView) findViewById(R.id.main_txt_TRScore);
        vsScoreTXT = (TextView) findViewById(R.id.main_txt_VSScore);
        ncScoreTXT = (TextView) findViewById(R.id.main_txt_NCScore);
    }

}
