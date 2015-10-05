package com.example.dren.cmput301assign1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Multiplayer_menu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiplayer_menu, menu);
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

    public void player2(View v){
        startActivity(new Intent(Multiplayer_menu.this, Two_players.class));
    }
    public void player3(View v){
        startActivity(new Intent(Multiplayer_menu.this, Three_player.class));

    }
    public void player4(View v){
        startActivity(new Intent(Multiplayer_menu.this, Four_player.class));

    }
    public void stats(View v){
        MultiGameController.clearStats();
        startActivity(new Intent(Multiplayer_menu.this, MultiStats.class));

    }
}
