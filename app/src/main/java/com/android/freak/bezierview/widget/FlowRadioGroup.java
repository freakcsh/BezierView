package com.android.freak.bezierview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by Administrator on 2018/10/13.
 */

public class FlowRadioGroup extends RadioGroup {
    private final String TAG = "RadioGroup";

    public FlowRadioGroup(Context context) {
        super(context);
    }

    public FlowRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        //调用ViewGroup的方法，测量子view
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int maxWidth = 0;//最大的宽度
        int totalHeight = 0;//累计的高
        int lineWidth = 0;//当前这一行的宽
        int maxLineHeight = 0;//当前这一行的最大高度

        //记录换行前的行和高
        int oldWidth;
        int oldHeight;

        int count = getChildCount();
        //假设 widthMode和heightMode都是AT_MOST
        /**
         * ①、UNSPECIFIED(未指定)，父元素部队自元素施加任何束缚，子元素可以得到任意想要的大小；
         * ②、EXACTLY(完全)，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
         * ③、AT_MOST(至多)，子元素至多达到指定大小的值。
         */
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);//获取子view的位置
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

            //得到这一行的最高
            oldHeight = maxLineHeight;
            //当前最大宽度
            oldWidth = maxWidth;
            int deltaX = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;//得到子view的宽度（包括leftMargin和rightMargin）
            //如果子view的宽度加上在此view之前此行的宽度（也就是绘制新的子view之前这一行的宽度）> 父布局的宽度，则换行
            if (lineWidth + deltaX + getPaddingLeft() + getPaddingRight() > widthSize) {
                //和目前最大的宽度比较,得到最宽。不能加上当前的child的宽,所以用的是oldWidth
                maxWidth = Math.max(lineWidth, oldWidth);
                //重置宽度（此时的宽度是在上一行已经放不下子view，则另起一行的宽度就是此时子view的宽度）
                lineWidth = deltaX;
                //累加高度
                totalHeight += oldHeight;
                //重置行高,当前这个View，属于下一行，因此当前最大行高为这个child的高度加上margin
                maxLineHeight = child.getMeasuredHeight() + params.bottomMargin + params.topMargin;

            } else {
                //不换行，累加宽度
                lineWidth += deltaX;
                //获取当前的高度
                int deltaY = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
                //重置最大的高度
                maxLineHeight = Math.max(maxLineHeight, deltaY);
            }

            if (i == count - 1) {
                //前面不换行的情况下没有加上下一行的高，如果是最后一行，还要再叠加上最后一行的最高的值
                totalHeight += maxLineHeight;
                //计算最后一行和前面的最宽的一行比较
                maxWidth = Math.max(lineWidth, oldWidth);
            }

        }

        //加上当前容器的padding值
        maxWidth += getPaddingLeft() + getPaddingRight();
        totalHeight += getPaddingTop() + getPaddingBottom();
        /**
         * MeasureSpec.EXACTLY 确定的尺寸
         */
        setMeasuredDimension(widthModel == MeasureSpec.EXACTLY ? widthSize : maxWidth,
                heightModel == MeasureSpec.EXACTLY ? heightSize : totalHeight);
    }

    /**
     * @param changed 这是此视图的新大小或位置
     * @param l       左侧位置，相对于父容器
     * @param t       最高位置，相对于父容器
     * @param r       正确的位置，相对于父容器
     * @param b       底部位置，相对于父容器
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
        int count = getChildCount();//获取子view的个数
        //pre为前面所有的child的相加后的位置
        int preLeft = getPaddingLeft();
        int preTop = getPaddingTop();
        //记录每一行的最高值
        int maxHeight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);//获取子控件
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            //r-l为当前容器的宽度。如果子view的累积宽度大于容器宽度，就换行。
            if (preLeft + params.leftMargin + params.rightMargin + child.getMeasuredWidth() + getPaddingRight() > (r - l)) {
                //重置
                preLeft = getPaddingLeft();
                //要选择child的height最大的作为设置
                preTop = preTop + maxHeight;
                maxHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            } else {
                //不换行
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + params.topMargin + params.bottomMargin);
            }
            //left坐标
            int left = preLeft + params.leftMargin;
            //top坐标
            int top = preTop + params.topMargin;
            int right = left + child.getMeasuredWidth();
            int bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
            preLeft += child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
        }
    }
}
