/**
 * 
 */
package com.hayukleung.analogclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.view.View;

import com.cndatacom.cdcutils.method.LogMgr;

/**
 * 自定义文本显示控件</br>
 * 该自定义控件中的文本可以在9个方位进行控制</br>
 * 左上——中上——右上</br>
 * 左中——中中——右中</br>
 * 左下——中下——右下</br>
 * 
 * @see http://blog.csdn.net/carrey1989/article/details/10399727
 * @author hayukleung
 *
 */
public class CenterTextView extends View {

    private Context mContext;
    /** 要显示的文字 */
    private String mText;
    /** 文字的颜色 */
    private int mTextColor;
    /** 文字的大小 */
    private int mTextSize;
    /** 文字的方位 */
    private int mTextAlign;

    // public static final int TEXT_ALIGN_CENTER = 0x00000000;
    /** 居左 */
    public static final int TEXT_ALIGN_LEFT = 0x00000001;
    /** 居右 */
    public static final int TEXT_ALIGN_RIGHT = 0x00000010;
    /** 竖直居中 */
    public static final int TEXT_ALIGN_CENTER_VERTICAL = 0x00000100;
    /** 水平居中 */
    public static final int TEXT_ALIGN_CENTER_HORIZONTAL = 0x00001000;
    /** 居顶 */
    public static final int TEXT_ALIGN_TOP = 0x00010000;
    /** 居底 */
    public static final int TEXT_ALIGN_BOTTOM = 0x00100000;

    /** 文本中轴线X坐标 */
    private float mTextCenterX;
    /** 文本baseline线Y坐标 */
    private float mTextBaselineY;

    /** 控件的宽度 */
    private int mViewWidth;
    /** 控件的高度 */
    private int mViewHeight;
    /** 控件画笔 */
    private Paint mPaint;

    private FontMetrics mFontMetrics;

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CenterTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public CenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    /**
     * @param context
     */
    public CenterTextView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void showLog(String log) {
        LogMgr.showLog(mContext, log, LogMgr.VERBOSE);
    }

    /**
     * 变量初始化
     */
    private void init() {
        mText = "";
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Align.CENTER);
        // 默认情况下文字居中显示
        mTextAlign = TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL;
        // 默认的文本颜色是黑色
        this.mTextColor = Color.BLACK;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mViewWidth = getWidth();
        mViewHeight = getHeight();
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas.save();
        showLog(CenterTextView.class.getSimpleName() + " " + mText + " " + canvas.getWidth() + " - " + canvas.getHeight());
        // canvas.translate(0, 0);
        // 绘制控件内容
        setTextLocation();
        canvas.drawText(mText, mTextCenterX, mTextBaselineY, mPaint);
        super.onDraw(canvas);
        // canvas.restore();
        
    }

    /**
     * 定位文本绘制的位置
     */
    private void setTextLocation() {
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mFontMetrics = mPaint.getFontMetrics();
        // 文本的宽度
        
        float textWidth = mPaint.measureText(mText);
        float textCenterVerticalBaselineY = mViewHeight / 2 - mFontMetrics.descent + (mFontMetrics.descent - mFontMetrics.ascent) / 2;
        switch (mTextAlign) {
        case TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL:
            mTextCenterX = (float) mViewWidth / 2;
            mTextBaselineY = textCenterVerticalBaselineY;
            break;
        case TEXT_ALIGN_LEFT | TEXT_ALIGN_CENTER_VERTICAL:
            mTextCenterX = textWidth / 2;
            mTextBaselineY = textCenterVerticalBaselineY;
            break;
        case TEXT_ALIGN_RIGHT | TEXT_ALIGN_CENTER_VERTICAL:
            mTextCenterX = mViewWidth - textWidth / 2;
            mTextBaselineY = textCenterVerticalBaselineY;
            break;
        case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_CENTER_HORIZONTAL:
            mTextCenterX = mViewWidth / 2;
            mTextBaselineY = mViewHeight - mFontMetrics.bottom;
            break;
        case TEXT_ALIGN_TOP | TEXT_ALIGN_CENTER_HORIZONTAL:
            mTextCenterX = mViewWidth / 2;
            mTextBaselineY = -mFontMetrics.ascent;
            break;
        case TEXT_ALIGN_TOP | TEXT_ALIGN_LEFT:
            mTextCenterX = textWidth / 2;
            mTextBaselineY = -mFontMetrics.ascent;
            break;
        case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_LEFT:
            mTextCenterX = textWidth / 2;
            mTextBaselineY = mViewHeight - mFontMetrics.bottom;
            break;
        case TEXT_ALIGN_TOP | TEXT_ALIGN_RIGHT:
            mTextCenterX = mViewWidth - textWidth / 2;
            mTextBaselineY = -mFontMetrics.ascent;
            break;
        case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_RIGHT:
            mTextCenterX = mViewWidth - textWidth / 2;
            mTextBaselineY = mViewHeight - mFontMetrics.bottom;
            break;
        }
    }

    /**
     * 设置文本内容
     * 
     * @param text
     */
    public void setText(String text) {
        this.mText = text;
        invalidate();
    }

    /**
     * 设置文本大小
     * 
     * @param textSizeSp
     *            文本大小，单位是sp
     */
    public void setTextSize(int textSizeSp) {
        DisplayParams displayParams = DisplayParams.getInstance(mContext);
        this.mTextSize = DisplayUtil.sp2px(textSizeSp, displayParams.fontScale);
        invalidate();
    }

    /**
     * 设置文本的方位
     */
    public void setTextAlign(int textAlign) {
        this.mTextAlign = textAlign;
        invalidate();
    }

    /**
     * 设置文本的颜色
     * 
     * @param textColor
     */
    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
        invalidate();
    }
}
