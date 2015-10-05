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

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dren on 10/3/15.
 */
public class GameStatsController {
    private static List<Integer> statistics = new ArrayList<Integer>();
    static String filename = "stats";
    //note figure out if code will break from empty array
    static public List<Integer> getStatistics(){

        if (statistics == null) {
            statistics = new ArrayList<Integer>();
        }
        return statistics;
    }

    public static void addStats(int stat){
        statistics.add(stat);
    }


    //http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android

    public static void loadFile(Context context){ //loads savefile and reads information into Stats
        FileInputStream inputStream;
        try{
            inputStream = context.openFileInput(filename);
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String pull;
            while((pull = bufferedReader.readLine()) != null){
                addStats(Integer.parseInt(pull));
            };
            inputStream.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }//end loadFile

    public static void saveReaction(Context context, int reaction){ //saves stats
        String sreaction = "" + reaction + "\n";

        FileOutputStream outputStream; //Saves to a file called stats

        try{
            outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(sreaction);
            writer.close();
            outputStream.close();
        }catch(FileNotFoundException e){
            try {
                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(sreaction);
                writer.close();
                outputStream.close();
            }catch(Exception f){
                f.printStackTrace();
            }
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void clearStats(Context context){ //clears savefile of data
        FileOutputStream outputStream;
        try{
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write("");
            writer.close();
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//wipes savefile

    public static void clearList(){ //clears current Arraylist
        statistics.clear();
    }//wipes arrayList of reaction times

    public static int minimumTime(int mode){//find min reaction time
        if(statistics.isEmpty()){
            return 0;
        }
        int minStat;
        int current;
        int i = getStartRange(mode);
        int x = statistics.size();

        minStat = statistics.get(i);
        while(i < x ){
            current = statistics.get(i);
            i++;
            if(current < minStat){
                minStat = current;
            }else{
                continue;
            }
        }
        return minStat;
    }

    public static int maximumTime(int mode){//find max reaction time
        if(statistics.isEmpty()){
            return 0;
        }
        int maxStat;
        int current;
        int i = getStartRange(mode);
        int x = statistics.size();

        maxStat = statistics.get(i);
        while(i < x ){
            current = statistics.get(i);
            i++;
            if(current > maxStat){
                maxStat = current;
            }else{
                continue;
            }
        }
        return maxStat;
    }
    public static int avgTime(int mode){ //calculates average time
        if(statistics.isEmpty()){
            return 0;
        }
        int avgTime = 0;
        int i = getStartRange(mode);
        int x = statistics.size();

        while(i < x){
            avgTime = avgTime + statistics.get(i);
            i++;
        }
        return (avgTime/x);
    }//end avgTime
    public static int medianTime(int mode){ //calculates median time
        if(statistics.isEmpty()){
            return 0;
        }
        int i = getStartRange(mode);
        int x = statistics.size();
        if(i != 0){
            if(mode == 1){
                return statistics.get(x - 5);
            }else if(mode == 2){
                return statistics.get(x - 100);
            }
        }
        //http://stackoverflow.com/questions/7446710/how-to-round-up-integer-division-and-have-int-result-in-java
        i = ((x + 2 - 1)/2);
        return statistics.get(i - 1);
    }

    public static int getStartRange(int mode){//calculates range of array to be used

        if(mode == 0){
            return 0;

        }else if(mode == 1){
            if(statistics.size() < 10){
                return 0;
            }else {
                return statistics.size() - 10;
            }
        }else {
            if (statistics.size() < 100) {
                return 0;
            } else {
                return statistics.size() - 100;
            }
        }

    }
    public static String compileString(){//compiles all information into one string to send
        String numbers = "";
        String buffer = "";
        int i = 0;
        int x = statistics.size();
        while(i < x){
            buffer = statistics.get(i).toString() + "ms\n";
            numbers = numbers.concat(buffer);
            i++;
        }
        return numbers;
    }
}
