package com.averagedistance001.averagedistance001;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Andreas on 18-04-14.
 */
public class MultitouchView extends View {
    private static final int SIZE = 5;
    private Paint mPaint;
    private Path mPath;
    private int[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA,
            Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
            Color.LTGRAY, Color.YELLOW };
    private Paint textPaint;


    public MultitouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        // set painter color to a color you like
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);
        mPaint.setStrokeWidth(6f);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Example sampled input trail
        canvas.drawCircle(104,280,SIZE,mPaint);
        canvas.drawCircle(154,280,SIZE,mPaint);
        canvas.drawCircle(173,280,SIZE,mPaint);
        canvas.drawCircle(193,280,SIZE,mPaint);
        canvas.drawCircle(230,280,SIZE,mPaint);
        canvas.drawCircle(267,280,SIZE,mPaint);
        canvas.drawCircle(300,280,SIZE,mPaint);
        canvas.drawCircle(332,280,SIZE,mPaint);
        canvas.drawCircle(360,280,SIZE,mPaint);
        canvas.drawCircle(376,280,SIZE,mPaint);
    }
}
