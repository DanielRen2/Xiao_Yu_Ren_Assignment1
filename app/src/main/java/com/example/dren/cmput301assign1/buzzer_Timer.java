/*
Copyright [2015] [Xiao Yu Ren]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

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
