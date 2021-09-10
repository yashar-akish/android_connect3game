package com.company.connect3game;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    Button playAgain;
    TextView winnerText;
    GridLayout gridLayout;
    ConstraintLayout constraintLayout;
    MediaPlayer mediaPlayer;

    int activePlayer = 0;
    boolean gameActive = true;
    int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    String winner = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgain =  findViewById(R.id.playAgainButton);
        winnerText = findViewById(R.id.winnerTextView);
        gridLayout = findViewById(R.id.gridLayout);
        constraintLayout = findViewById(R.id.constrainLayout);

        mediaPlayer = MediaPlayer.create(this,R.raw.coin_drop);
        gameOver();
    }

    private void gameOver() {

//        for (int[] winningPosition : winningPositions) {
//            if ((gameStatus[winningPosition[0]] != gameStatus[winningPosition[1]]
//                    || gameStatus[winningPosition[1]] == gameStatus[winningPosition[2]])
//                    && gameStatus[winningPosition[0]] != 2
//                    && !Arrays.asList(gameStatus).contains(2) && winner == "") {
//
//                gameActive = false;
//                constraintLayout.setBackgroundColor(Color.parseColor("#a6e7ff"));
//                gridLayout.setVisibility(View.GONE);
//                winnerText.setText("Game Over");
//                winnerText.setVisibility(View.VISIBLE);
//                playAgain.setVisibility(View.VISIBLE);
//            }
//        }


//        for (int i =0; i< gameStatus.length; i++){
//            if ((gameStatus[gameStatus.length - 1] != 2) && winner == "") {
//
//                gameActive = false;
//
//                constraintLayout.setBackgroundColor(Color.parseColor("#a6e7ff"));
//                gridLayout.setVisibility(View.GONE);
//                winnerText.setText("Game Over");
//
//
//                winnerText.setVisibility(View.VISIBLE);
//                playAgain.setVisibility(View.VISIBLE);
//            }
//        }
        /////////////////////////////////////////////////////////////////
        
//        for (int[] winningPosition : winningPositions) {
//            if (!Arrays.asList(gameStatus).contains(2) && winner == ""){
//
//
//            }
//        }

    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameStatus[tappedCounter] == 2 && gameActive) {

            gameStatus[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            mediaPlayer.seekTo(0);
            mediaPlayer.start();

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);


            for (int[] winningPosition : winningPositions) {

                if (gameStatus[winningPosition[0]] == gameStatus[winningPosition[1]]
                        && gameStatus[winningPosition[1]] == gameStatus[winningPosition[2]]
                        && gameStatus[winningPosition[0]] != 2) {

                    gameActive = false;

                    if (activePlayer == 1) {
                        winner = "Red";
                    } else {
                        winner = "Blue";
                    }

                    constraintLayout.setBackgroundColor(Color.parseColor("#a6e7ff"));
                    gridLayout.setVisibility(View.GONE);
                    winnerText.setText(winner + " has won");

                    if (winner == "Red") {
                        winnerText.setTextColor(Color.parseColor("#ff0000"));
                    } else if (winner == "Blue") {
                        winnerText.setTextColor(Color.parseColor("#0000ff"));
                    }
                    winnerText.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        gridLayout.setVisibility(View.VISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer = 0;
        gameActive = true;
        for (int i = 0; i < gameStatus.length; i++){
            gameStatus[i] = 2;
        }
        constraintLayout.setBackgroundColor(Color.parseColor("#ffffff"));
    }

}