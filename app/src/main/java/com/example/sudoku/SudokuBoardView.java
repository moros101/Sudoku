package com.example.sudoku;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class SudokuBoardView extends View{

    private final int boardColor;
    private final Paint boardColorPaint = new Paint();
    private int cellSize;
    public SudokuBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.SudokuBoardView,0,0);

        try{
            boardColor = a.getInteger(R.styleable.SudokuBoardView_boardColor,0);

        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width,height);

        int dimension = Math.min(this.getMeasuredWidth(),this.getMeasuredHeight());
        // getWidth() => width of view when displayed on screen
        // getMeasuredWidth() => display what view actually wants its width to be
        cellSize = dimension / 9;
        setMeasuredDimension(dimension,dimension);

    }

    @Override
    protected void onDraw(Canvas canvas){

        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        canvas.drawRect(0,0,getWidth(),getHeight(),boardColorPaint);
        drawBoard(canvas);
    }

    private void drawThickLine(){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(8);
        boardColorPaint.setColor(boardColor);
    }
    private void drawThinLine(){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(4);
        boardColorPaint.setColor(boardColor);
    }
    private void drawBoard(Canvas canvas){

        // draw cols
        for(int i=0; i<10; i++){
            if(i%3==0) drawThickLine();

            else drawThinLine();

            canvas.drawLine(cellSize*i,0,cellSize*i,getHeight(),boardColorPaint);
        }
        // draw rows
        for(int i=0; i<10; i++){
            if(i%3==0) drawThickLine();

            else drawThinLine();

            canvas.drawLine(0,cellSize*i,getWidth(),cellSize*i,boardColorPaint);
        }


    }
}
