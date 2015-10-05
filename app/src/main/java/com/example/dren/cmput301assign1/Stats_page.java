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

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Stats_page extends ActionBarActivity {
    //private ListView statsList;
    //ArrayAdapter<Integer> adapter;
    private int mode = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_page);

        GameStatsController.loadFile(this);

        TextView displayMode = (TextView) findViewById(R.id.display_mode);
        displayMode.setText("Use all Reaction Times");

        List<Integer> reactions = GameStatsController.getStatistics();
        System.out.println(reactions);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats_page, menu);
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

    public void allTime(View v){//button for all time mode
        TextView displayMode = (TextView) findViewById(R.id.display_mode);
        displayMode.setText("Use all Reaction Times");
        if(mode != 0){
            TextView displayStat = (TextView) findViewById(R.id.display_stat);
            displayStat.setText("Display Stats");
        }
        mode = 0;

    }

    public void last10(View v){//button for last 10 stats mode
        TextView displayMode = (TextView) findViewById(R.id.display_mode);
        displayMode.setText("Use Last 10 Reaction Times");
        if(mode != 1){
            TextView displayStat = (TextView) findViewById(R.id.display_stat);
            displayStat.setText("Display Stats");
        }
        mode = 1;

    }
    public void last100(View v){//button for last 100 stat mode
        TextView displayMode = (TextView) findViewById(R.id.display_mode);
        displayMode.setText("Use Last 100 Reaction Times");
        if(mode != 2){
            TextView displayStat = (TextView) findViewById(R.id.display_stat);
            displayStat.setText("Display Stats");
        }
        mode = 2;

    }
    public void minTime(View v){//button to display minTime
        TextView displayStat = (TextView) findViewById(R.id.display_stat);
        displayStat.setText(GameStatsController.minimumTime(mode) + " ms");

    }
    public void maxTime(View v){//Button to display Maxtime
        TextView displayStat = (TextView) findViewById(R.id.display_stat);
        displayStat.setText(GameStatsController.maximumTime(mode) + " ms");

    }
    public void avgTime(View v){//button to display avg time
        TextView displayStat = (TextView) findViewById(R.id.display_stat);
        displayStat.setText(GameStatsController.avgTime(mode) + " ms");

    }
    public void medianTime(View v){//button to display median time
        TextView displayStat = (TextView) findViewById(R.id.display_stat);
        displayStat.setText(GameStatsController.medianTime(mode) + " ms");

    }
    public void clearStat(View v){
        GameStatsController.clearStats(this);
        GameStatsController.clearList();
        TextView displayStat = (TextView) findViewById(R.id.display_stat);
        displayStat.setText("Stats Cleared");
    }

}
