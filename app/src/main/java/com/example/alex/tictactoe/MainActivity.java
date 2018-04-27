package com.example.alex.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    // 0 for yellow , 1 for red
    int nBoard = 0;
    boolean gameIsActive = true;

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2} , {3,4,5} , {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
//
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotationBy(360).setDuration(200);

            for(int[] winningPos : winningPositions) {
                if (gameState[winningPos[0]] == gameState[winningPos[1]] &&
                        gameState[winningPos[1]] == gameState[winningPos[2]] &&
                        gameState[winningPos[0]] != 2) {


                    gameIsActive = false;
                    String winner = "Red";
                    if ((gameState[winningPos[0]]) == 0) {
                        winner = "Yellow";
                    }
                    setWinnerLayoutVisible(winner);

                } else {

                    boolean gameIsOver = true;

                    for (int i=0 ; i<gameState.length ; i++) {
                        if (gameState[i] == 2) {
                            gameIsOver = false;
                        }
                    }

                    if (gameIsOver) {
                        setWinnerLayoutVisible("Draw");
                    }
                }
            }
        }
    }

    public void setWinnerLayoutVisible(String winner){
        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
        if(!winner.equals("Draw")) {
            winnerMessage.setText(winner + " has won !");
        } else if(winner.equals("Draw")) {
            winnerMessage.setText("It's a draw!");
        }
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.VISIBLE);

    }

    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for(int i= 0 ; i< gameState.length ; i++){
            gameState[i] = 2;
        }
        //GridLayout gridLayout = GridLayout findViewById(R.id.gridLayout); crashes the program
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0 ; i < gridLayout.getChildCount() ; i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    public boolean isWinningPos(int[] arr){
        int sum = 0;
        for(int i = 0 ; i < gameState.length ; i++){
            if (arr[i] == 2)
                return false;
        }
        return false;
    }

    public int getnSize(){
        return nBoard;
    }

    public void setBoardSize(int n){
        nBoard = n;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
