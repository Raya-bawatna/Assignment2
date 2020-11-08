package raya.cs.birzeit.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Timer extends AppCompatActivity {

    private int seconds=70;
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Intent intent=new Intent();

        if(savedInstanceState !=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");


        }


        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("seconds", seconds);
        bundle.putBoolean("running", running);

    }


    public void onClickStart(View view) {
        running=true;

    }

    public void onClickPause(View view) {
        running = false;
    }

    public void onClickStop(View view) {
        seconds = 70;
        running = false;

    }
    private void runTimer(){
        final TextView txtView = (TextView) findViewById(R.id.txtView);



        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds/3600;
                int minutes = seconds % 3600 /60;
                int snds = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, snds);


                if(snds>=0) {

                    txtView.setText(time);

                }
                if(running){
                    --seconds;


                }
                handler.postDelayed(this, 1000);
            }
        });
    }



}