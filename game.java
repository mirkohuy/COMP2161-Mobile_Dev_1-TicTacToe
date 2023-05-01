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
import android.widget.Toast;

import java.util.Random;

public class game extends AppCompatActivity {

    // declare variables and objects
    Button buttonCell11, buttonCell12, buttonCell13, buttonCell21, buttonCell22, buttonCell23, buttonCell31, buttonCell32, buttonCell33, buttonNewGame, buttonBack;
    TextView viewGameText;
    TextView viewGameTitle;
    SharedPreferences shareGame;
    int whosTurn = 0;
    int numOfPlayersGame;
    int compChoice;
    int p1scoreGame;
    int p2scoreGame;
    int compScoreGame;
    boolean isPlaying = false;
    boolean turnTaken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        Log.i("TAG", "game.java: onCreate()");

        // shared preferences objects
        shareGame = getSharedPreferences("data", Context.MODE_PRIVATE);
        numOfPlayersGame = shareGame.getInt("sharedNumOfPlayers", 0);
        Log.i("TAG","game.java: got from shared preferences: " + numOfPlayersGame);
        viewGameTitle = (TextView)findViewById(R.id.gameTitle);
        viewGameTitle.setText(numOfPlayersGame + " Player Game");

        // get scores from shared preferences
        p1scoreGame = shareGame.getInt("sharedP1score", 0);
        p2scoreGame = shareGame.getInt("sharedP2score", 0);
        compScoreGame = shareGame.getInt("sharedCompScore", 0);

        // assign objects to XML elements
        buttonCell11 = findViewById(R.id.cell11);
        buttonCell12 = findViewById(R.id.cell12);
        buttonCell13 = findViewById(R.id.cell13);
        buttonCell21 = findViewById(R.id.cell21);
        buttonCell22 = findViewById(R.id.cell22);
        buttonCell23 = findViewById(R.id.cell23);
        buttonCell31 = findViewById(R.id.cell31);
        buttonCell32 = findViewById(R.id.cell32);
        buttonCell33 = findViewById(R.id.cell33);
        buttonNewGame = findViewById(R.id.newGame);
        buttonBack = findViewById(R.id.goGameToMenu);

        buttonCell11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeTurn(buttonCell11);
            }
        });

        buttonCell12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: button12 pressed");
                takeTurn(buttonCell12);
            }
        });

        buttonCell13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: button13 pressed");
                takeTurn(buttonCell13);
            }
        });

        buttonCell21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: button21 pressed");
                takeTurn(buttonCell21);
            }
        });

        buttonCell22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: button22 pressed");
                takeTurn(buttonCell22);
            }
        });

        buttonCell23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: button23 pressed");
                takeTurn(buttonCell23);
            }
        });

        buttonCell31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: button31 pressed");
                takeTurn(buttonCell31);
            }
        });

        buttonCell32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: button32 pressed");
                takeTurn(buttonCell32);
            }
        });

        buttonCell33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: button33 pressed");
                takeTurn(buttonCell33);
            }
        });

        // button to start new game
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","game.java: new game button was pressed");
                isPlaying = true;
                buttonCell11.setText("");
                buttonCell12.setText("");
                buttonCell13.setText("");
                buttonCell21.setText("");
                buttonCell22.setText("");
                buttonCell23.setText("");
                buttonCell31.setText("");
                buttonCell32.setText("");
                buttonCell33.setText("");
                setFirstTurn();
                if(whosTurn == 2 && numOfPlayersGame == 1) {
                    compMove();
                    switchTurn();
                }
            }
        });

        // button to return to the main menu
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGameToMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentGameToMenu);
            }
        });
    }

    // method to decide who goes first
    void setFirstTurn(){
        whosTurn = new Random().nextInt(2) + 1; // generates a 1 or a 2
        Log.i("TAG","game.java: setFirstTurn() generated a " + whosTurn);
        viewGameText = (TextView)findViewById(R.id.gameText);
        isPlaying = true;
        if (whosTurn == 1){
            viewGameText.setText("It's player one's turn! X");
            Log.i("TAG","game.java: setFirstTurn(): player one going first");
        }
        else if(whosTurn == 2){
            viewGameText.setText("It's player two's turn! O");
            Log.i("TAG","game.java: setFirstTurn(): player two going first");
        }
    }

    // method to check for win
    void checkForWin(){
        Log.i("TAG","game.java: checkForWin()");
        viewGameText = (TextView)findViewById(R.id.gameText);
        int winner = 0;
        // check for row 1 win for X
        if(buttonCell11.getText().equals("X") && buttonCell12.getText().equals("X") && buttonCell13.getText().equals("X")){
            winner = 1;
        }
        // check for row 1 win for O
        if(buttonCell11.getText().equals("O") && buttonCell12.getText().equals("O") && buttonCell13.getText().equals("O")){
            winner = 2;
        }
        // check for row 2 win for X
        if(buttonCell21.getText().equals("X") && buttonCell22.getText().equals("X") && buttonCell23.getText().equals("X")){
            winner = 1;
        }
        // check for row 2 win for O
        if(buttonCell21.getText().equals("O") && buttonCell22.getText().equals("O") && buttonCell23.getText().equals("O")){
            winner = 2;
        }
        // check for row 3 win for X
        if(buttonCell31.getText().equals("X") && buttonCell32.getText().equals("X") && buttonCell33.getText().equals("X")){
            winner = 1;
        }
        // check for row 3 win for O
        if(buttonCell31.getText().equals("O") && buttonCell32.getText().equals("O") && buttonCell33.getText().equals("O")){
            winner = 2;
        }
        // check for col 1 win for X
        if(buttonCell11.getText().equals("X") && buttonCell21.getText().equals("X") && buttonCell31.getText().equals("X")){
            winner = 1;
        }
        // check for col 1 win for O
        if(buttonCell11.getText().equals("O") && buttonCell21.getText().equals("O") && buttonCell31.getText().equals("O")){
            winner = 2;
        }
        // check for col 2 win for X
        if(buttonCell12.getText().equals("X") && buttonCell22.getText().equals("X") && buttonCell32.getText().equals("X")){
            winner = 1;
        }
        // check for col 2 win for O
        if(buttonCell12.getText().equals("O") && buttonCell22.getText().equals("O") && buttonCell32.getText().equals("O")){
            winner = 2;
        }
        // check for col 3 win for X
        if(buttonCell31.getText().equals("O") && buttonCell32.getText().equals("O") && buttonCell33.getText().equals("O")){
            winner = 1;
        }
        // check for col 3 win for O
        if(buttonCell31.getText().equals("O") && buttonCell32.getText().equals("O") && buttonCell33.getText().equals("O")){
            winner = 2;
        }
        // check for col 3 win for X
        if(buttonCell13.getText().equals("X") && buttonCell23.getText().equals("X") && buttonCell33.getText().equals("X")){
            winner = 1;
        }
        // check for col 3 win for O
        if(buttonCell13.getText().equals("O") && buttonCell23.getText().equals("O") && buttonCell33.getText().equals("O")){
            winner = 2;
        }
        // check for first diagonal win for X
        if(buttonCell11.getText().equals("X") && buttonCell22.getText().equals("X") && buttonCell33.getText().equals("X")){
            winner = 1;
        }
        // check for first diagonal win for O
        if(buttonCell11.getText().equals("O") && buttonCell22.getText().equals("O") && buttonCell33.getText().equals("O")){
            winner = 2;
        }
        // check for second diagonal win for X
        if(buttonCell13.getText().equals("X") && buttonCell22.getText().equals("X") && buttonCell31.getText().equals("X")){
            winner = 1;
        }
        // check for second diagonal win for O
        if(buttonCell13.getText().equals("O") && buttonCell22.getText().equals("O") && buttonCell31.getText().equals("O")){
            winner = 2;
        }
        switch(winner) {
            case 1:
                viewGameText.setText("Player one wins!");
                p1scoreGame += 1;
                fillWithBlanks();
                isPlaying = false;
                break;
            case 2:
                viewGameText.setText("Player two wins!");
                switch (numOfPlayersGame) {
                    case 1:
                        compScoreGame += 1;
                        break;
                    case 2:
                        p2scoreGame += 1;
                        break;
                }
                fillWithBlanks();
                isPlaying = false;
                break;
        }
        SharedPreferences.Editor editorGame = shareGame.edit();
        editorGame.putInt("sharedP1score", p1scoreGame);
        editorGame.putInt("sharedP2score", p2scoreGame);
        editorGame.putInt("sharedCompScore", compScoreGame);
        editorGame.commit();
    }

    // method for taking turn
    void takeTurn(Button currentButton){
        viewGameText = (TextView)findViewById(R.id.gameText);
        switch(numOfPlayersGame){
            case 1:
                isPlaying = true;
                viewGameText.setText("It's player one's turn! X");
                buttonHit1p(currentButton);
                switchTurn();
                checkForWin();
                compMove();
                switchTurn();
                checkForWin();
                break;
            case 2:
                buttonHit2p(currentButton);
                switchTurn();
                checkForWin();
                break;
        }
    }

    // method to take turn when one player game
    void buttonHit1p(Button currentButton){
        if(currentButton.getText() == ""){
            currentButton.setText("X");
        }else{
            Toast.makeText(getApplicationContext(),"Can't go there! :(.",Toast.LENGTH_SHORT).show();
        }
    }

    // method to control button hit logic during two player game
    void buttonHit2p(Button currentButton){
        if(currentButton.getText() == "") {
            if (isPlaying == false) {
                isPlaying = true;
                whosTurn = 1;
                currentButton.setText("X");
            } else {
                switch (whosTurn) {
                    case 1:
                        currentButton.setText("X");
                        turnTaken = true;
                        break;
                    case 2:
                        currentButton.setText("O");
                        turnTaken = true;
                        break;
                }
            }
        }else{
            Toast.makeText(getApplicationContext(),"Can't go there! :(.",Toast.LENGTH_SHORT).show();
        }
    }

    // method for computer taking turn
    void compMove(){
        boolean compValidChoice = false;
        if(isPlaying == true) {
            do {
                compChoice = new Random().nextInt(9) + 1; // generates 1-9
                Log.i("TAG", "game.java: compMove(): generated a " + compChoice);
                switch (compChoice) {
                    case 1:
                        if (buttonCell11.getText() == "") {
                            buttonCell11.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 11");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;
                    case 2:
                        if (buttonCell12.getText() == "") {
                            buttonCell12.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 12");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;
                    case 3:
                        if (buttonCell13.getText() == "") {
                            buttonCell13.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 13");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;
                    case 4:
                        if (buttonCell21.getText() == "") {
                            buttonCell21.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 21");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;
                    case 5:
                        if (buttonCell22.getText() == "") {
                            buttonCell22.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 22");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;
                    case 6:
                        if (buttonCell23.getText() == "") {
                            buttonCell23.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 23");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;
                    case 7:
                        if (buttonCell31.getText() == "") {
                            buttonCell31.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 31");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;
                    case 8:
                        if (buttonCell32.getText() == "") {
                            buttonCell32.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 32");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;
                    case 9:
                        if (buttonCell33.getText() == "") {
                            buttonCell33.setText("O");
                            Log.i("TAG", "game.java: compMove(): comp placed O on 33");
                            compValidChoice = true;
                        } else {
                            compValidChoice = false;
                        }
                        break;

                }
            } while (compValidChoice == false);
        }
    }

    // method to switch the turn
    void switchTurn(){
        viewGameText = (TextView)findViewById(R.id.gameText);
        switch(whosTurn) {
            case (1):
                whosTurn = 2;
                Log.i("TAG", "game.java: switchTurn(): switched to player 2 turn");
                viewGameText.setText("It's player two's turn! O");
                break;
            case (2):
                whosTurn = 1;
                Log.i("TAG", "game.java: switchTurn(): switched to player 1 turn");
                viewGameText.setText("It's player one's turn! X");
                break;
        }
    }

    // method to fill empty squares when a game is finished
    void fillWithBlanks(){
        if(buttonCell11.getText() == ""){
            buttonCell11.setText(" ");
        }
        if(buttonCell12.getText() == ""){
            buttonCell12.setText(" ");
        }
        if(buttonCell13.getText() == ""){
            buttonCell13.setText(" ");
        }
        if(buttonCell21.getText() == ""){
            buttonCell21.setText(" ");
        }
        if(buttonCell22.getText() == ""){
            buttonCell22.setText(" ");
        }
        if(buttonCell23.getText() == ""){
            buttonCell23.setText(" ");
        }
        if(buttonCell31.getText() == ""){
            buttonCell31.setText(" ");
        }
        if(buttonCell32.getText() == ""){
            buttonCell32.setText(" ");
        }
        if(buttonCell33.getText() == ""){
            buttonCell33.setText(" ");
        }
    }
}