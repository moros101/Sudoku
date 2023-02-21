package com.moros101.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SudokuBoardView gameBoard;
    private Solver gameBoardSolver;

    private Button solveBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBoard = findViewById(R.id.sudokuBoardView);
        gameBoardSolver = gameBoard.getSolver();

        solveBTN = findViewById(R.id.solve_btn);
    }

    public void BTNOnePress(View view){
        gameBoardSolver.setNumPos(1);
        gameBoard.invalidate();
    }

    public void BTNTwoPress(View view){
        gameBoardSolver.setNumPos(2);
        gameBoard.invalidate();
    }

    public void BTNThreePress(View view){
        gameBoardSolver.setNumPos(3);
        gameBoard.invalidate();
    }
    public void BTNFourPress(View view){
        gameBoardSolver.setNumPos(4);
        gameBoard.invalidate();
    }
    public void BTNFivePress(View view){
        gameBoardSolver.setNumPos(5);
        gameBoard.invalidate();
    }
    public void BTNSixPress(View view){
        gameBoardSolver.setNumPos(6);
        gameBoard.invalidate();
    }
    public void BTNSevenPress(View view){
        gameBoardSolver.setNumPos(7);
        gameBoard.invalidate();
    }
    public void BTNEightPress(View view){
        gameBoardSolver.setNumPos(8);
        gameBoard.invalidate();
    }
    public void BTNNinePress(View view){
        gameBoardSolver.setNumPos(9);
        gameBoard.invalidate();
    }

    public void solve(View view){
        if(solveBTN.getText().toString().equals("SOLVE")){
            solveBTN.setText("CLEAR");
        }else{
            solveBTN.setText("SOLVE");
        }
    }
}