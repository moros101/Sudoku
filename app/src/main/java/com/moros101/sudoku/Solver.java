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
    // update board with new num
    private void setNumPos(int num){
        if(this.selected_col != -1 && this.selected_row != -1){
            if(this.board[this.selected_row][this.selected_col] == num){
                this.board[this.selected_row][this.selected_col] = 0;
            }else{
                this.board[this.selected_row][this.selected_col] = num;
            }
        }
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
