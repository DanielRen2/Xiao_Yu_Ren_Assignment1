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

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SinglePlayerMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_menu);

        Button back = (Button) findViewById(R.id.BackSingle);
        Button play = (Button) findViewById(R.id.SinglePlayer);


        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }

        });

        play.setOnClickListener(new View.OnClickListener() {
            PopupWindow pwindow;

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popup = inflater.inflate(R.layout.reaction_instructions, (ViewGroup) findViewById(R.id.popup_element));

                pwindow = new PopupWindow(popup, 850, 850, true);
                pwindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                //http://stackoverflow.com/questions/15171227/closing-the-popup-in-android

                Button exit = (Button) popup.findViewById(R.id.exitInstrc);
                exit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View popup) {
                        startActivity(new Intent(SinglePlayerMenu.this, SinglePlayer.class));
                        pwindow.dismiss();
                    }

                });
            }//end on click
        });//end on click listener
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_player_menu, menu);
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

    public void findStats(View v){
        GameStatsController.clearList();
        startActivity(new Intent(SinglePlayerMenu.this, Stats_page.class));
    }

}
