package com.android.freak.bezierview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.android.freak.bezierview.R;

/**
 * Created by Administrator on 2018/10/12.
 */

/**
 * 构造方法
 * PathMeasure()	创建一个空的PathMeasure
 * PathMeasure(Path path, boolean forceClosed)	创建 PathMeasure 并关联一个指定的Path(Path需要已经创建完成)。
 * <p>
 * 只要方法解释
 * void	setPath(Path path, boolean forceClosed)	关联一个Path
 * boolean	isClosed()	是否闭合
 * float	getLength()	获取Path的长度
 * boolean	nextContour()	跳转到下一个轮廓(也就是跳转到下一个曲线上)
 * boolean	getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)	截取片段
 * boolean	getPosTan(float distance, float[] pos, float[] tan)	获取指定长度的位置坐标及该点切线值
 * boolean	getMatrix(float distance, Matrix matrix, int flags)	获取指定长度的位置坐标及该点Matrix
 */
public class PathMeasureView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    public PathMeasureView(Context context) {
        super(context, null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 10;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        /**
         * boolean getPosTan (float distance, float[] pos, float[] tan) 返回值(boolean)	判断获取是否成功	true表示成功，数据会存入 pos 和 tan 中，
         * false 表示失败，pos 和 tan 不会改变
         * 参数说明：
         * distance	距离 Path 起点的长度	取值范围: 0 <= distance <= getLength
         * pos	该点的坐标值	当前点在画布上的位置，有两个数值，分别为x，y坐标。
         * tan	该点的正切值	当前点在曲线上的方向，使用 Math.atan2(tan[1], tan[0]) 获取到正切角的弧度值。
         */
        Path path = new Path();                                 // 创建 Path

//        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形
        RectF rectF = new RectF(-200, 200, 200, -200);
        path.addRect(rectF, Path.Direction.CW);

        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

//        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
//
//        mMatrix.reset();                                                        // 重置Matrix
//
//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
//
//        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
//
//        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合
//
//
//        canvas.drawPath(path, mPaint);                                   // 绘制 Path
//        canvas.drawBitmap(mBitmap, mMatrix, mPaint);                     // 绘制箭头
//
//        invalidate();                                                           // 重绘页面,没有调用此方法，则箭头不会按路径移动,不推荐使用此方法，推荐使用
/***************************第二种方式获取矩阵，相对于第一种更加简便****************************/
        // 获取当前位置的坐标以及趋势的矩阵
        /**
         * POSITION_MATRIX_FLAG(位置)  只使用这个则不会旋转
         * TANGENT_MATRIX_FLAG(正切)    只使用这个则不会再路径上移动，只能旋转
         * 需要注意一些内容:
         *
         *        1.对 matrix 的操作必须要在 getMatrix 之后进行，否则会被 getMatrix 重置而导致无效。
         *       2.矩阵对旋转角度默认为图片的左上角，我们此处需要使用 preTranslate 调整为图片中心。
         *      3.pre(矩阵前乘) 与 post(矩阵后乘) 的区别
         */

        measure.getMatrix(measure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);

        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);   // <-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)

        canvas.drawPath(path, mPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);                     // 绘制箭头

        invalidate();                                                           // 重绘页面
    }
}
