// COMP2161 T00705586 Huy Mirko Nov 2022

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // declare variables
    Button buttonNames, button1pGame, button2pGame, buttonStandings;
    SharedPreferences shareMain;
    int numOfPlayersMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Log.i("TAG","MainActivity.java: onCreate()");

        // shared preferences objects
        shareMain = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorMain = shareMain.edit();

        // assign objects to XML elements
        buttonNames = findViewById(R.id.goMenuToNames);
        button1pGame = findViewById(R.id.choose1p);
        button2pGame = findViewById(R.id.choose2p);
        buttonStandings = findViewById(R.id.goMenuToStandings);

        // go from main menu to names activity
        buttonNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMenuToNames = new Intent(getApplicationContext(), names.class);
                startActivity(intentMenuToNames);
            }
        });

        // go from main menu to 1 player game activity
        button1pGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1pGame = new Intent(getApplicationContext(), game.class);
                startActivity(intent1pGame);
                numOfPlayersMain = 1;
                editorMain.putInt("sharedNumOfPlayers", numOfPlayersMain);
                editorMain.commit();
            }
        });

        // go from main menu to 2 player game activity
        button2pGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2pGame = new Intent(getApplicationContext(), game.class);
                startActivity(intent2pGame);
                numOfPlayersMain = 2;
                editorMain.putInt("sharedNumOfPlayers", numOfPlayersMain);
                editorMain.commit();
            }
        });

        // go from main menu to standings activity
        buttonStandings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMenuToStandings = new Intent(getApplicationContext(), standings.class);
                startActivity(intentMenuToStandings);
            }
        });
    }
}