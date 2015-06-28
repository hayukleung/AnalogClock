package com.hayukleung.analogclock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class RotateTextView extends TextView {

    private static final int DEFAULT_DEGREES = 0;
    private int mDegrees;
    private Context mContext;

    public RotateTextView(Context context) {
        super(context, null);
        this.mContext = context;
        init(null);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.textViewStyle);
        this.mContext = context;
        init(attrs);
    }
    
    public RotateTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init(attrs);
    }
    
    private void init(AttributeSet attrs) {
        this.setGravity(Gravity.CENTER);
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.RotateTextView);
        mDegrees = a.getDimensionPixelSize(R.styleable.RotateTextView_degree, DEFAULT_DEGREES);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
    
    /* (non-Javadoc)
     * @see android.widget.TextView#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // TODO Auto-generated method stub
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        canvas.rotate(mDegrees, this.getWidth() / 2f, this.getHeight() / 2f);
        super.onDraw(canvas);
        canvas.restore();
    }

    public void setDegrees(int degrees) {
        mDegrees = degrees;
    }
}
