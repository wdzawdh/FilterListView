/**
 *   @function:$
 *   @description: $
 *   @param:$
 *   @return:$
 *   @history:
 * 1.date:$ $
 *           author:$
 *           modification:
 */

package com.cw.filterlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;
import java.util.List;

/**
 * @author Cw
 * @date 16/8/19
 */
public class IndexBarView extends View {

    String[] items = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"
            , "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    List<String> mItemsList = Arrays.asList(items);

    private Paint mPaint;
    private int mChooseIndex = -1;
    private int mWidth;
    private int mHeight;
    private int mSingleHeight;

    public IndexBarView(Context context) {
        super(context);
        init();
    }

    public IndexBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);//粗体
        mPaint.setAntiAlias(true);
    }

    public void setItems(List<String> list) {
        this.mItemsList = list;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mItemsList.size(); i++) {
            mPaint.setColor(Color.parseColor("#1094FC"));
            mPaint.setTextSize(dip2px(12));
            //算出每一个字合适的开始绘制位置
            float xPos = mWidth - mPaint.measureText(mItemsList.get(i)) / 2 - dip2px(20);
            float yPos = mSingleHeight * (i + 1);

            if (i == mChooseIndex) {
                mPaint.setColor(Color.parseColor("#1094FC"));
                mPaint.setTextSize(dip2px(30));
                //字体放大时向左偏移100px
                canvas.drawText(mItemsList.get(i), xPos - 150, yPos, mPaint);
            } else {
                canvas.drawText(mItemsList.get(i), xPos, yPos, mPaint);
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置单个字体的高度为indexBar测量高度的1/28
        mSingleHeight = getMeasuredHeight() / 28;
        //计算整个indexBar的最终高度
        mHeight = mSingleHeight * mItemsList.size();
        //这个值需要根据放大字体的大小变化而变化,否则显示不全
        mWidth = (int) dip2px(80);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST
                && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, mHeight);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        //当前选择的角标
        mChooseIndex = (int) (event.getY() / mHeight * mItemsList.size());

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mChooseIndex >= 0 && mChooseIndex < mItemsList.size()) {
                    if (listener != null) {
                        listener.onTouchingLetterChanged(mItemsList.get(mChooseIndex), mChooseIndex);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mChooseIndex >= 0 && mChooseIndex < mItemsList.size()) {
                    if (listener != null) {
                        listener.onTouchingLetterChanged(mItemsList.get(mChooseIndex), mChooseIndex);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                mChooseIndex = -1;
                invalidate();
                break;
        }
        return true;
    }

    private float dip2px(int dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    OnTouchTypeChangedListener listener;

    public void setOnTouchTypeChangedListener(OnTouchTypeChangedListener listener) {
        this.listener = listener;
    }

    public interface OnTouchTypeChangedListener {
        void onTouchingLetterChanged(String s, int index);
    }
}