package com.example.dren.cmput301assign1;

import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.Log;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

/**
 * Created by dren on 10/1/15.
 */
public class buzzer_Timer{

    public static int Rtimer_gen(){

        Random num = new Random();

        int number = num.nextInt((2000 - 10) + 1) + 10;

        return number;

    }
    public static long getTime(){
        return System.currentTimeMillis();
    }


}
