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
import android.widget.TextView;

public class standings extends AppCompatActivity {

    // declare variables and objects
    Button buttonReset, buttonBack;
    SharedPreferences shareStandings;
    TextView viewP1, viewP2, viewP1score, viewP2score, viewCompScore;
    int p1scoreInt, p2scoreInt, compScoreInt;
    String name1, name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        getSupportActionBar().hide();
        Log.i("TAG","standings.java: onCreate()");

        // assign objects to XML elements
        buttonReset = findViewById(R.id.resetScores);
        buttonBack = findViewById(R.id.goStandingsToMenu);
        viewP1 = (TextView)findViewById(R.id.p1textView);
        viewP2 = (TextView)findViewById(R.id.p2textView);
        viewP1score = (TextView)findViewById(R.id.p1score);
        viewP2score = (TextView)findViewById(R.id.p2score);
        viewCompScore = (TextView)findViewById(R.id.compScore);

        // shared preferences
        shareStandings = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorNames = shareStandings.edit();
        name1 = shareStandings.getString("sharedP1name", "Player 1");
        name2 = shareStandings.getString("sharedP2name", "Player 2");
        Log.i("TAG","standings.java: Got the names :" + name1 + " and " + name2);
        p1scoreInt = shareStandings.getInt("sharedP1score", 0);
        p2scoreInt = shareStandings.getInt("sharedP2score", 0);
        compScoreInt = shareStandings.getInt("sharedCompScore", 0);

        viewP1.setText(name1 + "'s Score: ");
        viewP2.setText(name2 + "'s Score: ");
        viewP1score.setText(String.valueOf(p1scoreInt));
        viewP2score.setText(String.valueOf(p2scoreInt));
        viewCompScore.setText(String.valueOf(compScoreInt));

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","standings.java: reset button was pressed");
                editorNames.putInt("sharedP1score", 0);
                editorNames.putInt("sharedP2score", 0);
                editorNames.putInt("sharedCompScore", 0);
                editorNames.commit();
                p1scoreInt = 0;
                p2scoreInt = 0;
                compScoreInt = 0;
                viewP1score.setText(String.valueOf(p1scoreInt));
                viewP2score.setText(String.valueOf(p2scoreInt));
                viewCompScore.setText(String.valueOf(compScoreInt));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intentStandingsToMenu = new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(intentStandingsToMenu);
            }
        });
    }
}