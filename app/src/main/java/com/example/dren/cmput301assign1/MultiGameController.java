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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dren on 10/4/15.
 */
public class MultiGameController {
    private static List<String> multi_statistics = new ArrayList<String>();

    static public List<String> getStatistics(){

        if (multi_statistics == null) {
            multi_statistics = new ArrayList<String>();
        }
        return multi_statistics;
    }


    public static void loadFile(Context context, String filename){//loads from file into list
        FileInputStream inputStream;
        try{
            inputStream = context.openFileInput(filename);
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String pull;
            while((pull = bufferedReader.readLine()) != null){
                multi_statistics.add(pull);
            };
            inputStream.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }//end loadFile

    public static void saveReaction(Context context, String winner, String filename){//saves stats
        String winnerFormat = winner + " buzzes\n";

        FileOutputStream outputStream; //Saves to a file called stats

        try{
            outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(winnerFormat);
            writer.close();
            outputStream.close();
        }catch(FileNotFoundException e){
            try {
                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(winnerFormat);
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

    public static void clearStats(){//clears list
        multi_statistics.clear();
    }

    public static void clearSave(Context context){//clears savefile
        //FileOutputStream outputStream; //Saves to a file called stats
        String filename = "statsMulti";
        int i = 2;

        try{
            while(i < 5) {
                FileOutputStream outputStream; //Saves to a file called stats
                outputStream = context.openFileOutput(filename + i, Context.MODE_PRIVATE);
                i ++;
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write("");
                writer.close();
                outputStream.close();
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String compileStats(Context context) {//compiles stats into string for sending through email
        String filename = "statsMulti";
        String stats = "";
        String buffer = "'";
        int i = 2;

        while (i < 5) {
            clearStats();
            loadFile(context, filename + i);
            int n = 0;
            while(n < multi_statistics.size()){
                buffer = multi_statistics.get(n) + "\n";
                stats = stats.concat(buffer);
                n++;
            }
            i++;
            clearStats();
        }
        return stats;
    }




}
