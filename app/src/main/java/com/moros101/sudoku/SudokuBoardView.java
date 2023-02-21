package com.moros101.sudoku;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;


public class SudokuBoardView extends View{

    private final int boardColor;
    private final int cellFillColor;
    private final int cellsHighlightColor;
    private final int letterColor;
    private final int letterColorSolve;
    private final Paint boardColorPaint = new Paint();
    private final Paint cellFillColorPaint = new Paint();
    private final Paint cellsHighlightColorPaint = new Paint();
    private final Paint letterPaint = new Paint();
    private final Rect letterPaintBounds = new Rect();
    private int cellSize;

    // gives data about touch events that happen on user screen
    private final Solver solver = new Solver();

    public SudokuBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.SudokuBoardView,0,0);

        try{
            boardColor = a.getInteger(R.styleable.SudokuBoardView_boardColor,0);
            cellFillColor = a.getInteger(R.styleable.SudokuBoardView_cellFillColor,0);
            cellsHighlightColor = a.getInteger(R.styleable.SudokuBoardView_cellsHighlightColor,0);
            letterColor = a.getInteger(R.styleable.SudokuBoardView_letterColor,0);
            letterColorSolve = a.getInteger(R.styleable.SudokuBoardView_letterColorSolve,0);

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

        cellFillColorPaint.setStyle(Paint.Style.FILL);
//        cellFillColorPaint.setStrokeWidth(16);
        cellFillColorPaint.setColor(cellFillColor);
        cellFillColorPaint.setAntiAlias(true);

        cellsHighlightColorPaint.setStyle(Paint.Style.FILL);
//        cellsHighlightColorPaint.setStrokeWidth(16);
        cellsHighlightColorPaint.setColor(cellsHighlightColor);
        cellsHighlightColorPaint.setAntiAlias(true);

        colorCell(canvas, solver.getSelected_row(), solver.getSelected_col());
        canvas.drawRect(0,0,getWidth(),getHeight(),boardColorPaint);
        drawBoard(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean isValid;

        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            solver.setSelected_row((int) Math.ceil(y/cellSize));
            solver.setSelected_col((int) Math.ceil(x/cellSize));
            isValid = true;
        }else{
            isValid = false;
        }
        System.out.println("check: "+isValid);
        return isValid;
    }

    private void drawNum(Canvas canvas){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solver.getBoard()[i][j] != 0){
                    String text = Integer.toString(solver.getBoard()[i][j]);
                    float width,height;

                    letterPaint.getTextBounds(text,0,text.length(),letterPaintBounds);
                    width = letterPaint.measureText(text);
                    height = letterPaintBounds.height();

                    canvas.drawText(text,(j*cellSize)+(cellSize-width)/2, (i*cellSize)+(cellSize-height)/2,letterPaint);
                }
            }
        }
    }

    private void colorCell(Canvas canvas,int r,int c){
        if(solver.getSelected_col() != -1 && solver.getSelected_row() != -1){
            canvas.drawRect((c-1)*cellSize,0,c*cellSize,getHeight(),cellsHighlightColorPaint);
            canvas.drawRect(0,(r-1)*cellSize,getWidth(),r*cellSize,cellsHighlightColorPaint);
            canvas.drawRect((c-1)*cellSize,(r-1)*cellSize,c*cellSize,r*cellSize,cellFillColorPaint);
        }
        invalidate();
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
