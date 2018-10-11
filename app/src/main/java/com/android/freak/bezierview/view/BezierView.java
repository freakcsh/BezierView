package com.android.freak.bezierview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/10/9.
 */

public class BezierView extends View {
    Paint mPaint = new Paint();
    private int mWidth, mHeight;

    public BezierView(Context context) {
        super(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);

    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w / 2;
        mHeight = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth, mHeight);
        canvas.scale(1, -1);
//        mPaint.setColor(Color.RED);
//        canvas.drawLine(0, -2000, 0, 2000, mPaint);
//        canvas.drawLine(-2000, 0, 2000, 0, mPaint);

        Path path = new Path();
        mPaint.setColor(Color.BLUE);
//        path.moveTo(0, 25);
//        path.cubicTo(0, 25, 100, 100, 100, 0);
//        path.cubicTo(100,0,100,-75,0,-150);
//        path.cubicTo(0,-150,-100,-75,-100,0);
//        path.cubicTo(-100,0,-100,100,0,25);
        path.moveTo(0, 20);
        path.cubicTo(0, 100, 100, 100, 100, 0);
        path.quadTo(100,-100,0,-180);
//        path.cubicTo(100,-100,100,-75,0,-100);
//        path.cubicTo(0,-100,-100,-100,-100,0);
        path.quadTo(-100,-100,-100,0);
        path.cubicTo(-100,100,0,100,0,20);
        canvas.drawPath(path, mPaint);

    }
}
