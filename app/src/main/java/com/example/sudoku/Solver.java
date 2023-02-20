package com.example.sudoku;

public class Solver {

    private static int selected_row;
    private static int selected_col;

    Solver(){
        selected_row = -1;
        selected_col = -1;
    }

    public static int getSelected_row() {
        return selected_row;
    }

    public static void setSelected_row(int selected_row) {
        Solver.selected_row = selected_row;
    }

    public static int getSelected_col() {
        return selected_col;
    }

    public static void setSelected_col(int selected_col) {
        Solver.selected_col = selected_col;
    }
}
