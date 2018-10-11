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
 * Created by Administrator on 2018/10/11.
 */

public class TestView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;

    public TestView(Context context) {
        super(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.translate(mWidth,mHeight);
        canvas.scale(1,-1);
        Path path=new Path();
        path.moveTo(-100,0);
        path.quadTo(0,100,100,0);
        mPaint.setColor(Color.RED);
//        path.quadTo();
        canvas.drawPath(path,mPaint);
    }
}
