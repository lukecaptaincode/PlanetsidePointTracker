package com.lukecaptaincode.planetsidepointtracker;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button trAPoint;
    mainController mC;
    CountDownTimer [] timers;
    TextView trScoreTXT;
    int trScore, vsScore, ncScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mC = new mainController();
        timers = new CountDownTimer[12];
        trScore = 0;
        vsScore = 0;
        ncScore =0;
        initButtons();
    }

    public void initButtons()
    {
        trAPoint = (Button) findViewById(R.id.main_btn_ApointTR);
        trAPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startA_TRTimer();
            }
        });
    }
    public void initTextView()
    {
        trScoreTXT = (TextView) findViewById(R.id.main_txt_TRScore);
    }

    public void startA_TRTimer()
    {
        timers[0]= new CountDownTimer(60000, 100) {

            public void onTick(long millisUntilFinished) {
                trAPoint.setText(""+ millisUntilFinished / 1000);

                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                addPoint_TR();
                timers[0].start();
            }

        }.start();
    }
    public void addPoint_TR()
    {
        trScore = trScore +1;
        trScoreTXT.setText(Integer.toString(trScore));
    }
}
