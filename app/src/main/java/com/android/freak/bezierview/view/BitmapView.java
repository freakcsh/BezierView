package com.android.freak.bezierview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.freak.bezierview.R;

/**
 * Created by Administrator on 2018/10/11.
 */

public class BitmapView extends View {
    private int mLeafHeight;
    private int mLeafWidth;
    private Paint mPaint;
    private Bitmap mLeafBitmap;
    public BitmapView(Context context) {
        super(context,null);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mLeafBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.leaf)).getBitmap();
        mLeafWidth = mLeafBitmap.getWidth();
        mLeafHeight = mLeafBitmap.getHeight();
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setFilterBitmap(true);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mLeafBitmap,mLeafWidth,mLeafHeight,mPaint);
    }
}
