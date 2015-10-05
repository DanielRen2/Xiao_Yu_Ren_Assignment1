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

import java.util.ArrayList;
import java.util.List;

public class MultiStats extends ActionBarActivity {
    private ListView statsList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_stats);

        //gets initial data for adapter

        MultiGameController.loadFile(this, "statsMulti2");
        List<String> playerList = MultiGameController.getStatistics();
        //adapter code to make a list view work
        adapter = new ArrayAdapter<String>(this, R.layout.adapter_list, playerList);
        statsList = (ListView) findViewById(R.id.MultiList);
        statsList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multi_stats, menu);
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

    public void button2p(View v){//saves multiplayer stats in two player mode
        TextView typeList = (TextView) findViewById(R.id.player_display);
        typeList.setText("Two Player Winner information");
        MultiGameController.clearStats();
        MultiGameController.loadFile(this, "statsMulti2");//sends context and filename for two player
        List<String> playerList = MultiGameController.getStatistics();
        adapter.notifyDataSetChanged();//resets adapter

    }
    public void button3p(View v){
        TextView typeList = (TextView) findViewById(R.id.player_display);
        typeList.setText("Three Player Winner information");
        MultiGameController.clearStats();
        MultiGameController.loadFile(this, "statsMulti3");
        List<String> playerList = MultiGameController.getStatistics();
        adapter.notifyDataSetChanged();

    }
    public void button4p(View v){
        TextView typeList = (TextView) findViewById(R.id.player_display);
        typeList.setText("Four Player Winner information");
        MultiGameController.clearStats();
        MultiGameController.loadFile(this, "statsMulti4");
        List<String> playerList = MultiGameController.getStatistics();
        adapter.notifyDataSetChanged();

    }
    public void clearStatMulti(View v){
        MultiGameController.clearStats();
        MultiGameController.clearSave(this);
        adapter.notifyDataSetChanged();
        TextView typeList = (TextView) findViewById(R.id.player_display);
        typeList.setText("All Player Saved Data Cleared");
    }
}
