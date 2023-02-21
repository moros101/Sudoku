package com.moros101.sudoku;

import java.util.ArrayList;

public class Solver {

    int board[][];
    ArrayList<ArrayList<Object>> emptyBoxIndex;
    int selected_row;
    int selected_col;

    Solver(){
        selected_row = -1;
        selected_col = -1;

        board = new int[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                board[i][j] = 0;
            }
        }
        emptyBoxIndex = new ArrayList<>();
    }


    private boolean check(int r, int c){
        if(this.board[r][c] > 0){

            for(int i=0; i<9; i++){
                // number present in row
                if(this.board[i][c] == this.board[r][c] && r!=i){
                    return false;
                }
                // number present in col
                if(this.board[r][i] == this.board[r][c] && c!=i){
                    return false;
                }
            }
            // number present in 3x3 grid
            int boxRow = r/3;
            int boxCol = c/3;

            for(int i=boxRow*3; i<boxRow*3 + 3; i++){
                for(int j=boxCol*3; j<boxCol*3 + 3; j++){

                    if(this.board[i][j] == this.board[r][c] && r!=i && c!=j){
                        return false;
                    }
                }
            }

        }
        return true;
    }

    public boolean solve(SudokuBoardView display){

        int r = -1;int c = -1;
        // finds first empty cell
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(this.board[i][j] == 0){
                    r = i;
                    c = j;
                    break;
                }
            }
        }
        if(r == -1 || c == -1){
            return true;
        }
        for(int i=1; i<10; i++){
            this.board[r][c] = i;
            display.invalidate();

            if(check(r,c)){
                if(solve(display)){
                    return true;
                }
            }

            //backtrack
            this.board[r][c] = 0;
        }

        return false;
    }

    // extract rows and cols of empty box i.e. 0
    public void getEmptyBoxIndexs(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(this.board[i][j] == 0){
                    this.emptyBoxIndex.add(new ArrayList<>());
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size()-1).add(i);
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size()-1).add(j);
                }
            }
        }
    }

    public void resetBoard(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                this.board[i][j] = 0;
            }
        }
        this.emptyBoxIndex = new ArrayList<>();
    }
    // update board with new num
    public void setNumPos(int num){
        if(this.selected_col != -1 && this.selected_row != -1){
            if(this.board[this.selected_row-1][this.selected_col-1] == num){
                this.board[this.selected_row-1][this.selected_col-1] = 0;
            }else{
                this.board[this.selected_row-1][this.selected_col-1] = num;
            }
        }
        System.out.println("check: "+this.board[this.selected_row-1][this.selected_col-1]);
    }

    public int [][] getBoard(){
        return this.board;
    }

    public ArrayList<ArrayList<Object>> getEmptyBoxIndex(){
        return this.emptyBoxIndex;
    }

    public int getSelected_row() {
        return selected_row;
    }

    public void setSelected_row(int selected_row) {
        this.selected_row = selected_row;
    }

    public int getSelected_col() {
        return selected_col;
    }

    public void setSelected_col(int selected_col) {
        this.selected_col = selected_col;
    }
}
