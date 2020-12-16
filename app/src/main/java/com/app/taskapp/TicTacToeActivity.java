package com.app.taskapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.taskapp.databinding.ActivityTicTacToeBinding;

public class TicTacToeActivity extends AppCompatActivity {


    private ActivityTicTacToeBinding binding;
    private int turn = 0;
    private int[][] currentGame;
    private ImageView[][] imageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicTacToeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        resetGame();
        setActions();

    }

    private void setActions() {
        imageViews =
                new ImageView[][]{
                        {binding.img00, binding.img01, binding.img02},
                        {binding.img10, binding.img11, binding.img12},
                        {binding.img20, binding.img21, binding.img22},
                };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int finalI = i;
                int finalJ = j;
                imageViews[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (currentGame[finalI][finalJ] == -1) {
                            if (turn == 0) {
                                imageViews[finalI][finalJ].setImageResource(R.drawable.ic_zero);
                                currentGame[finalI][finalJ] = 0;
                                turn = 1;
                                binding.tvStatus.setText("X Turn");
                            } else if (turn == 1) {
                                imageViews[finalI][finalJ].setImageResource(R.drawable.ic_close);
                                currentGame[finalI][finalJ] = 1;
                                turn = 0;
                                binding.tvStatus.setText("O Turn");
                            }
                            checkForWining();
                        }
                    }
                });
            }
        }

        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void checkForWining() {
        int winner = isWin();

        if (winner != -1) {
            if (winner == 0) {
                binding.tvStatus.setText("Hurrah | O Wins\nX Turn");
            } else if (winner == 1) {
                binding.tvStatus.setText("Hurrah | X Wins\nO Turn");
            }
            resetGame();
        }
    }

    private void resetGame() {
        currentGame = new int[][]{
                {-1, -1, -1},
                {-1, -1, -1},
                {-1, -1, -1}
        };

        if (imageViews != null) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    imageViews[i][j].setImageResource(0);
                }
            }
        }


    }

    private int isWin() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s.append(currentGame[i][j]).append(" | ");
            }
            s.append("\n");
        }
        Log.d("TAGER", "isWin:\n" + s);
        if (currentGame[0][0] == currentGame[0][1] && currentGame[0][1] == currentGame[0][2]) {
            return currentGame[0][0];
        } else if (currentGame[1][0] == currentGame[1][1] && currentGame[1][1] == currentGame[1][2]) {
            return currentGame[1][0];
        } else if (currentGame[2][0] == currentGame[2][1] && currentGame[2][1] == currentGame[2][2]) {
            return currentGame[2][0];
        } else if (currentGame[0][0] == currentGame[1][0] && currentGame[1][0] == currentGame[2][0]) {
            return currentGame[0][0];
        } else if (currentGame[0][1] == currentGame[1][1] && currentGame[1][1] == currentGame[2][1]) {
            return currentGame[0][1];
        } else if (currentGame[0][2] == currentGame[1][2] && currentGame[1][2] == currentGame[2][2]) {
            return currentGame[0][2];
        } else if (currentGame[0][0] == currentGame[1][1] && currentGame[1][1] == currentGame[2][2]) {
            return currentGame[0][0];
        } else if (currentGame[2][0] == currentGame[1][1] && currentGame[1][1] == currentGame[0][2]) {
            return currentGame[2][0];
        } else return -1;
    }
}