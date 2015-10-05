package com.example.dren.cmput301assign1;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Three_player extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_player);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_three_player, menu);
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


    public void button2_1(View v){
        MultiGameController.saveReaction(this, "Player 1" , "statsMulti3");
        popUp(v, "Player 1");

    }
    public void button2_2(View v){
        MultiGameController.saveReaction(this, "Player 2" , "statsMulti3");
        popUp(v, "Player 2");
    }
    public void button2_3(View v){
        MultiGameController.saveReaction(this, "Player 3" , "statsMulti3");
        popUp(v, "Player 3");
    }

    public void popUp(View v, String player) {

        final PopupWindow pwindow;

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popup = inflater.inflate(R.layout.reaction_instructions, (ViewGroup) findViewById(R.id.popup_element));

        pwindow = new PopupWindow(popup, 850, 850, true);
        pwindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        //http://stackoverflow.com/questions/15171227/closing-the-popup-in-android
        TextView winner = (TextView) popup.findViewById(R.id.textView3);

        winner.setText(player + " is the winner");
        winner.setTextSize(25);

        Button exit = (Button) popup.findViewById(R.id.exitInstrc);
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View popup) {
                pwindow.dismiss();
                finish();
            }

        });
    }
}
