package com.opticaline.groupactivity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.opticaline.groupactivity.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 13-11-21.
 */
public class GameView extends View {
    private int level;
    private int rows;
    private int columns;
    private Rect[][] rects;

    public GameView(Context context) {
        super(context);
        Log.i("GameView", "GameView(Context context)");
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("GameView", "GameView(Context context, AttributeSet ats)");
        settingAttrs(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        settingAttrs(context, attrs);
        Log.i("GameView", "GameView(Context context, AttributeSet ats, int defStyle)");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("GameView", "onMeasure(widthMeasureSpec, heightMeasureSpec)");
    }

    private void settingAttrs(Context context, AttributeSet attrs) {
        this.setDrawingCacheEnabled(false);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GameView);
        level = typedArray.getInt(R.styleable.GameView_level, 1);
        rows = level / 5 * 8;
        columns = level;
        rects = new Rect[rows][columns];
        paints = new Paint[rows][columns];
    }

    public boolean drawRect(int row, int column, int color) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(color));
        return drawRect(row, column, paint);
    }

    private ArrayList<int[]> array = new ArrayList<int[]>();

    private Paint[][] paints;

    public boolean drawRect(int row, int column, Paint paint) {
        if (row < this.rows && column < this.columns) {
            array.add(new int[]{row, column});
            paints[row][column] = paint;
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(25);

        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();

        int edgeLength = width / columns;
        if (edgeLength > (height / rows)) {
            edgeLength = height / rows;
        }

        int left = (width - edgeLength * columns) / 2;
        int top = (height - edgeLength * rows) / 2;
        Rect temp;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp = new Rect();
                temp.set(left + j * edgeLength,                //定位矩形左上角X轴坐标
                        top + i * edgeLength,                  //定位矩形左上角Y轴坐标
                        left + (j + 1) * edgeLength - 1,       //定位矩形右下角X轴坐标
                        top + (i + 1) * edgeLength - 1);       //定位矩形右下角Y轴坐标
                rects[i][j] = temp;
            }
        }

        for (int i = 0; i < array.size(); i++) {
            Log.i("GameView", "draw rect!");
            int row = array.get(i)[0];
            int column = array.get(i)[1];
            canvas.drawRect(rects[row][column], paints[row][column]);
        }
    }
}
