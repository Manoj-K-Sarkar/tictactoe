package com.example.android.tictactoe;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private final int h = 3;
    private final int w = 3;
    private Button[][] board = new Button[h][w];
    private int turn = 0;
    private Button btnRestart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                board[i][j] = findViewById(resID);
                board[i][j].setOnClickListener(this);
            }
        }

        btnRestart = findViewById(R.id.button_Restart);
        btnRestart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restartGame();
                    }
                }
        );

    }

    @Override
    public void onClick(View v) {
        String string = new String();
        String player = new String();
        if ((++turn) % 2 != 0) {
            string = "0";
            player = "Player1";
        }
        else {
            string = "X";
            player = "Player2";
        }
        ((Button) v).setText(string);
        if(CheckForVictory(string))
            victoryMessage(player);
        else if(turn == 9)
            draw();
    }

    public void draw(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("!Both Are You Play Well!\n");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("!!DRAW!!");
        builder.setMessage(buffer.toString());
        builder.show();
        restartGame();
    }

    public void victoryMessage(String player){
        StringBuffer buffer = new StringBuffer();
        buffer.append(" " + player + "\n");
        buffer.append("You Are The Winner!!\n");
        buffer.append("\n\nPress RESTART for play Again\n");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("!!Congratulation!!");
        builder.setMessage(buffer.toString());
        builder.show();
    }

    public void restartGame(){
        turn = 0;
        for(int i = 0;i < h ;i++){
            for(int j = 0;j < w;j++)
                board[i][j].setText("");
        }
    }

    public boolean CheckForVictory(String string) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w - 2; j++) {
                if (board[i][j].getText().toString() == board[i][j + 1].getText().toString()
                        && board[i][j + 1].getText().toString() == board[i][j + 2].getText().toString()
                        && board[i][j].getText().toString() == string)
                    return true;
            }
        }
        for (int j = 0; j < w; j++) {
            for (int i = 0; i < h - 2; i++) {
                if (board[i][j].getText().toString() == board[i + 1][j].getText().toString()
                        && board[i + 2][j].getText().toString() == board[i + 1][j].getText().toString()
                        && board[i + 1][j].getText().toString() == string)
                    return true;
            }
        }
        for (int i = 0; i < h - 2; i++) {
            for (int j = 0; j < w - 2; j++) {
                if (board[i][j].getText().toString() == board[i][j].getText().toString()
                        && board[i + 1][j + 1].getText().toString() == board[i][j].getText().toString()
                        && board[i + 2][j + 2].getText().toString() == string)
                    return true;
            }
        }
        for (int i = 2; i < h; i++) {
            for (int j = 0; j < w - 2; j++) {
                if (board[i][j].getText().toString() == board[i - 1][j + 1].getText().toString()
                        && board[i - 1][j + 1].getText().toString() == board[i - 2][j + 2].getText().toString()
                        && board[i - 2][j + 2].getText().toString() == string)
                    return true;
            }
        }

        return false;
    }
}
