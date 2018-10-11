package com.android.freak.bezierview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2018/10/10.
 */

public class CosWaveView extends View {
    //屏幕宽高
    private int mWidth, mHeight;
    //波浪线画笔
    private Paint mPaint;
    //一个波长的长度
    private int waveLength;
    //波浪的路径
    private Path mPath;
    /**
     * 平移偏移量
     */
    private int mOffset;

    /**
     * 一个屏幕内显示几个周期
     */
    private int mWaveCount;

    /**
     * 振幅
     */
    private int mWaveAmplitude;

    /**
     * 波形的颜色
     */
    private int waveColor = 0xaaFF7E37;
    //属性动画
    private ValueAnimator valueAnimator;

    public CosWaveView(Context context) {
        super(context, null);
    }

    public CosWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CosWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        /**
         * 振幅
         */
        mWaveAmplitude = dp2px(15);
        //一个周期波浪的长度
        waveLength = dp2px(300);
        initPaint();
    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPath = new Path();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(waveColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        /**
         * 加上1.5是为了保证至少有两个波形（屏幕外边一个完整的波形，屏幕里边一个完整的波形）
         */
        //（round）返回最接近参数的
        mWaveCount = (int) Math.round(mWidth / mHeight + 1.5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSinPath(canvas);
    }

    /**
     * sin函数图像的波形
     *
     * @param canvas
     */
    private void drawSinPath(Canvas canvas) {
        mPath.reset();

        mPath.moveTo(-waveLength + mOffset, mWaveAmplitude);

        // TODO: 2017/6/19   //相信很多人会疑惑为什么控制点的纵坐标是以下值,是根据公式计算出来的,具体计算方法情况文章内容

        for (int i = 0; i < mWaveCount; i++) {

            //第一个控制点的坐标为(-mWaveLength * 3 / 4,-mWaveAmplitude)
            mPath.quadTo(-waveLength + mOffset + i * waveLength, mWaveAmplitude,
                    -waveLength / 2 + mOffset + i * waveLength, mWaveAmplitude);


            //第二个控制点的坐标为(-waveLength / 4,3 * mWaveAmplitude)
            mPath.quadTo(-waveLength / 2 + mOffset + i * waveLength, 3 * mWaveAmplitude,
                    mOffset + i * waveLength, mWaveAmplitude);


        }

        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();

        canvas.drawPath(mPath, mPaint);
    }

    public void initAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, waveLength);
        //设置动画的长度。默认持续时间为300毫秒。
        valueAnimator.setDuration(2000);
        //start()调用延迟开始动画的时间量（以毫秒为单位） 。请注意，启动延迟应始终为非负数。在N及以上时，任何负启动延迟都将被钳位为0。
        valueAnimator.setStartDelay(300);
        //设置动画应重复的次数。如果重复计数为0，则不再重复动画。如果重复计数大于0或INFINITE，则将考虑重复模式。默认情况下，重复计数为0。
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //用于计算此动画的已用分数的时间插值器。插值器确定动画是以线性还是非线性运动运行，例如加速和减速。默认值为 AccelerateDecelerateInterpolator
        valueAnimator.setInterpolator(new LinearInterpolator());
        //将侦听器添加到在动画生命周期内发送更新事件的侦听器集。在计算动画的值之后，在动画的每个帧的所有侦听器上调用此方法。
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //ValueAnimator当只有一个属性被动画时，由此计算的最新值。该值仅在动画运行时才合理。
                // 此只读属性的主要用途是在计算值之后立即从ValueAnimator 调用期间检索值，
                // 该值ValueAnimator.AnimatorUpdateListener.onAnimationUpdate(ValueAnimator)在每个动画帧期间调用。
                mOffset = (int) animation.getAnimatedValue();
                //使整个视图无效。如果视图可见， onDraw(android.graphics.Canvas)将来会在某个时候调用。

                //必须从UI线程调用此方法。要从非UI线程调用，请调用 postInvalidate()。
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void startAnimation() {
        initAnimation();
    }

    public void stopAnimation() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }


    public void pauseAnimation() {
        if (valueAnimator != null) {
            valueAnimator.pause();
        }
    }

    public void resumeAnimation() {
        if (valueAnimator != null) {
            valueAnimator.resume();
        }
    }

}
