package com.hayukleung.analogclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.cndatacom.cdcutils.method.LogMgr;

/**
 * 指针
 * 
 * @author HayukLeung
 *
 */
public class ClockHandView extends View {
    
    private Context mContext;
    private Paint mPaint;
    private Animation mAnimator;
    /** 周期，单位毫秒 */
    private int mCycle;
    /** 指针初始角度，相对于竖直向上顺时针方向 */
    private float mStartAngle;
    /** 指针长度缩小系数 */
    private float mRadiusScale;
    /** 指针颜色 */
    private int mHandColorA;
    private int mHandColorR;
    private int mHandColorG;
    private int mHandColorB;
    // private RectF mRectF;

    public ClockHandView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    public ClockHandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public ClockHandView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }
    
    private void init() {
        this.mCycle = 60000;
        this.mStartAngle = 0;
        this.mRadiusScale = 1;
        this.mHandColorA = 0xFF;
        this.mHandColorR = 0xFF;
        this.mHandColorG = 0x00;
        this.mHandColorB = 0x00;
        // this.mRectF = new RectF();
        this.mPaint = new Paint();
        this.mAnimator = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // 动画重复次数(-1 表示一直重复)
        this.mAnimator.setRepeatCount(Animation.INFINITE);
        this.mAnimator.setInterpolator(new LinearInterpolator() {
            
            @Override
            public float getInterpolation(float input) {
                
                return super.getInterpolation(input);
            }
        });
        // 图片配置动画
        setAnimation(this.mAnimator);
        // setBackgroundColor(mContext.getResources().getColor(android.R.color.background_light));
        showLog("init()");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        showLog("onMeasure(): w --> " + widthMeasureSpec + " h --> " + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        showLog("onLayout(): changed --> " + changed);
        showLog("onLayout(): left    --> " + left);
        showLog("onLayout(): top     --> " + top);
        showLog("onLayout(): right   --> " + right);
        showLog("onLayout(): bottom  --> " + bottom);
        super.onLayout(changed, left, top, right, bottom);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        showLog(ClockHandView.class.getSimpleName() + " onDraw()" + " left   --> " + getLeft());
        showLog(ClockHandView.class.getSimpleName() + " onDraw()" + " right  --> " + getRight());
        showLog(ClockHandView.class.getSimpleName() + " onDraw()" + " top    --> " + getTop());
        showLog(ClockHandView.class.getSimpleName() + " onDraw()" + " bottom --> " + getBottom());
        super.onDraw(canvas);
        mPaint.setARGB(mHandColorA, mHandColorR, mHandColorG, mHandColorB);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShadowLayer(1, 1, 1, 0xff222222);
        
        // canvas.drawLine((getRight() - getLeft()) / 2, (getBottom() - getTop()) / 2, (getRight() - getLeft()) / 2, 0, mPaint);
        float startX = (getRight() - getLeft()) / 2;
        float startY = (getBottom() - getTop()) / 2;
        float radius = startX < startY ? startX : startY;
        
        // 线
        canvas.drawLine(startX, startY, getStopX(startX, radius * mRadiusScale, mStartAngle), getStopY(startY, radius * mRadiusScale, mStartAngle), mPaint);
        // TODO 矩形
        // canvas.drawRoundRect(mRectF, 1 / radius, 1 / radius, mPaint);
        
        // 动画开始到结束的执行时间(1000 = 1 秒)
        this.mAnimator.setDuration(mCycle);
    }
    
    /**
     * 开始走指针
     */
    public void run() {
        this.mAnimator.startNow();
    }
    
    /**
     * 设置指针走动周期，单位毫秒
     * 
     * @param cycle
     */
    public void setCycle(int cycle) {
        this.mCycle = cycle;
        invalidate();
    }
    
    /**
     * 设置初始角度
     * 
     * @param startAngle
     */
    public void setStartAngle(float startAngle) {
        this.mStartAngle = startAngle;
        invalidate();
    }
    
    /**
     * 设置指针长度缩小系数
     * 
     * @param radiusScale
     */
    public void setRadiusScale(float radiusScale) {
        this.mRadiusScale = radiusScale;
        invalidate();
    }
    
    /**
     * 设置指针颜色
     * 
     * @param a
     * @param r
     * @param g
     * @param b
     */
    public void setHandColor(int a, int r, int g, int b) {
        this.mHandColorA = a;
        this.mHandColorR = r;
        this.mHandColorG = g;
        this.mHandColorB = b;
        invalidate();
    }
    
    private void showLog(String log) {
        LogMgr.showLog(mContext, log, LogMgr.VERBOSE);
    }
    
    /**
     * 获取指针终止X坐标
     * 
     * @param centerX
     * @param radius
     * @param hour
     * @return
     */
    private float getStopX(float centerX, float radius, float angle) {
        float sin = (float) Math.sin(angle);
        return centerX + radius * sin;
    }

    /**
     * 获取指针终止Y坐标
     * 
     * @param centerY
     * @param radius
     * @param hour
     * @return
     */
    private float getStopY(float centerY, float radius, float angle) {
        float cos = (float) Math.cos(angle);
        return centerY - radius * cos;
    }
}
