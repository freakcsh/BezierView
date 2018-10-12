package com.android.freak.bezierview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/10/12.
 */

/**
 * Path布尔值操作
 */
public class PathBooleanOperation extends View {
    private Paint mPaint;
    private int mWidth, mHeight;

    public PathBooleanOperation(Context context) {
        super(context, null);
    }

    public PathBooleanOperation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public PathBooleanOperation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setColor(Color.BLACK);



        /*********************填充模式************************/
//        Path path = new Path();
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//        // 添加大正方形
//        path.addRect(-400, -400, 400, 400, Path.Direction.CCW);
        /**
         *   Path.FillType.WINDING  非零环绕数规则
         *   Path.FillType.EVEN_ODD 奇偶规则
         *   Path.FillType.INVERSE_EVEN_ODD 反奇偶规则
         *   Path.FillType.INVERSE_WINDING 反非零环绕数规则
         *
         */
//        path.setFillType(Path.FillType.EVEN_ODD);
//        path.setFillType(Path.FillType.INVERSE_EVEN_ODD)
//        path.setFillType(Path.FillType.WINDING);
//        path.setFillType(Path.FillType.INVERSE_WINDING);
//        canvas.drawPath(path, mPaint);
/*************************** 布尔操作************************************/
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);


//        canvas.drawPath(path1, mPaint);
//        canvas.drawPath(path2, mPaint);
//        canvas.drawPath(path3, mPaint);
//        canvas.drawPath(path4, mPaint);
        /**
         * Path.Op.DIFFERENCE              Path1中减去Path2后剩下的部分
         * Path.Op.UNION                   包含全部Path1和Path2
         *Path.Op.REVERSE_DIFFERENCE       Path2中减去Path1后剩下的部分
         *Path.Op.INTERSECT                Path1与Path2相交的部分
         *Path.Op.XOR 	                   包含Path1与Path2但不包括两者相交的部分
         *
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            path1.op(path2, Path.Op.DIFFERENCE);
            path1.op(path3, Path.Op.UNION);
            path1.op(path4, Path.Op.DIFFERENCE);
        }
        canvas.drawPath(path1, mPaint);

    }
}
