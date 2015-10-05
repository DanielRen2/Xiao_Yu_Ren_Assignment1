package com.example.dren.cmput301assign1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class SinglePlayer extends ActionBarActivity {

    static int okay = 0;
    long startTime = 0;
    static int random_time = buzzer_Timer.Rtimer_gen();
    static Handler handler = new Handler();
    Runnable runner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);


        //works for android specifically
        //http://stackoverflow.com/questions/3072173/how-to-call-a-method-after-a-delay-in-android

        handler.postDelayed(runner = new Runnable() {
            @Override
            public void run() {

                ImageView que = (ImageView) findViewById(R.id.imageView);
                Drawable picture = getResources().getDrawable(R.drawable.green_button);
                que.setImageDrawable(picture);
                okay = 1;
                startTime = buzzer_Timer.getTime();

            }
        }, random_time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tap(View v){

        if (okay == 1) {
            long endTime = buzzer_Timer.getTime();
            EditText status = (EditText) findViewById(R.id.message);
            int reaction = (int)(endTime - startTime);
            status.setText("Your time is " + reaction + "ms");
            Button tap = (Button) findViewById(R.id.tapper);
            tap.setEnabled(false);
            okay = 0;
            GameStatsController.saveReaction(this, reaction);

        } else {
            EditText status = (EditText) findViewById(R.id.message);
            status.setText("Not time yet");
            random_time = buzzer_Timer.Rtimer_gen();
            handler.removeCallbacks(runner);
            handler.postDelayed(runner, random_time);
        }
    }

}
