package com.bwie.zhaozenghui115;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class LuckyPan extends View implements View.OnClickListener {
    //颜色
    int[] mColor = new int[]{Color.RED, Color.BLACK, Color.BLUE, Color.YELLOW, Color.GREEN, Color.MAGENTA};
    boolean isstart = false;
    private Paint mpaint;
    private int mpading;
    private int measuredWidth;
    private RotateAnimation rotateAnimation;
    private RectF rectF;

    public LuckyPan(Context context) {
        this(context, null);
    }

    public LuckyPan(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuckyPan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化笔
        initpaint();
        setAnim();
        //设置点击事件
        setOnClickListener(this);
    }

    private void initpaint() {

        mpaint = new Paint();
        mpaint.setColor(Color.BLACK);
        mpaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(300, 300);
        measuredWidth = getMeasuredWidth();
        mpading = 5;
        rectF = new RectF(0, 0, measuredWidth, measuredWidth);



    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mpaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(measuredWidth / 2, measuredWidth / 2, measuredWidth / 2 - mpading, mpaint);
        mpaint.setStyle(Paint.Style.FILL);

        setRoat(canvas);

        //设置里面的小圆
        canvas.drawCircle(measuredWidth / 2, measuredWidth / 2, 50, mpaint);

    }

    private void setRoat(Canvas canvas) {

        for (int i = 0; i < mColor.length; i++) {
            mpaint.setColor(mColor[i]);
            canvas.drawArc(rectF, (i - 1) * 60 + 60, 60, true, mpaint);
        }

    }

    public void setAnim() {
        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
    }

    public void setRow() {

        int setwit = 360 / mColor.length;


    }


    @Override
    public void onClick(View v) {
        if (!isstart) {
            isstart = true;
            rotateAnimation.setDuration(1000);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            startAnimation(rotateAnimation);
        } else {
            isstart = false;
            setsttop();
        }
    }

    private void setsttop() {
        clearAnimation();
    }
}
