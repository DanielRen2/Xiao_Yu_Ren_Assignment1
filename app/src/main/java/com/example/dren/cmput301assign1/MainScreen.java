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

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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

    public void SinglePlayer(View v){ //calls button Single Player
        startActivity(new Intent(MainScreen.this, SinglePlayerMenu.class));
    }

    public void MultiPlayer(View v){ //calls button Single Player
        startActivity(new Intent(MainScreen.this, Multiplayer_menu.class));
    }
    public void email(View v){//sends all stat data as an email
        //http://www.tutorialspoint.com/android/android_sending_email.htm
        String recipient = "dren@ualberta.ca";
        Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipient);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Statistics");
        GameStatsController.loadFile(this); //loads data into statslist in GameStatsController
        String dataStats = GameStatsController.compileString(); //compiles stats into string from GamesStatsController
        dataStats = dataStats.concat(MultiGameController.compileStats(this));//adds stats from Multi player to string
        emailIntent.putExtra(Intent.EXTRA_TEXT, dataStats);
        GameStatsController.clearList();
        try {
            startActivity(Intent.createChooser(emailIntent, "Choose a client"));
        }catch(android.content.ActivityNotFoundException ex){
            Toast.makeText(MainScreen.this, "No email client installed.", Toast.LENGTH_SHORT).show();

        }

    }

}
