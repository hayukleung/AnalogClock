package com.hayukleung.analogclock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.cndatacom.cdcutils.method.LogMgr;

/**
 * 数字时钟
 * 
 * @author HayukLeung
 * 
 */
public class AnalogClockView extends ViewGroup {

    private Context mContext;
    private SimpleDateFormat mSimpleDateFormat;
    private Date mDate;

    public AnalogClockView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    public AnalogClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public AnalogClockView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        
        mSimpleDateFormat = new SimpleDateFormat("HH-mm-ss", Locale.getDefault());
        mDate = new Date();
        
        final int txtSizeBig = (int) mContext.getResources().getDimension(R.dimen.text_size_48);
        final int txtSizeSmall = (int) mContext.getResources().getDimension(R.dimen.text_size_40);
        final int txtColor = mContext.getResources().getColor(android.R.color.holo_green_light);

        int padding = 0;

        CenterTextView txt1 = new CenterTextView(mContext);
        txt1.setText("1");
        txt1.setTextSize(txtSizeBig);
        txt1.setTextColor(txtColor);
        txt1.setBackground(null);
        txt1.setPadding(padding, padding, padding, padding);
        addView(txt1, 0);

        CenterTextView txt2 = new CenterTextView(mContext);
        txt2.setText("2");
        txt2.setTextSize(txtSizeBig);
        txt2.setTextColor(txtColor);
        txt2.setBackground(null);
        txt2.setPadding(padding, padding, padding, padding);
        addView(txt2, 1);

        CenterTextView txt3 = new CenterTextView(mContext);
        txt3.setText("3");
        txt3.setTextSize(txtSizeBig);
        txt3.setTextColor(txtColor);
        txt3.setBackground(null);
        txt3.setPadding(padding, padding, padding, padding);
        addView(txt3, 2);

        CenterTextView txt4 = new CenterTextView(mContext);
        txt4.setText("4");
        txt4.setTextSize(txtSizeBig);
        txt4.setTextColor(txtColor);
        txt4.setBackground(null);
        txt4.setPadding(padding, padding, padding, padding);
        addView(txt4, 3);

        CenterTextView txt5 = new CenterTextView(mContext);
        txt5.setText("5");
        txt5.setTextSize(txtSizeBig);
        txt5.setTextColor(txtColor);
        txt5.setBackground(null);
        txt5.setPadding(padding, padding, padding, padding);
        addView(txt5, 4);

        CenterTextView txt6 = new CenterTextView(mContext);
        txt6.setText("6");
        txt6.setTextSize(txtSizeBig);
        txt6.setTextColor(txtColor);
        txt6.setBackground(null);
        txt6.setPadding(padding, padding, padding, padding);
        addView(txt6, 5);

        CenterTextView txt7 = new CenterTextView(mContext);
        txt7.setText("7");
        txt7.setTextSize(txtSizeBig);
        txt7.setTextColor(txtColor);
        txt7.setBackground(null);
        txt7.setPadding(padding, padding, padding, padding);
        addView(txt7, 6);

        CenterTextView txt8 = new CenterTextView(mContext);
        txt8.setText("8");
        txt8.setTextSize(txtSizeBig);
        txt8.setTextColor(txtColor);
        txt8.setBackground(null);
        txt8.setPadding(padding, padding, padding, padding);
        addView(txt8, 7);

        CenterTextView txt9 = new CenterTextView(mContext);
        txt9.setText("9");
        txt9.setTextSize(txtSizeBig);
        txt9.setTextColor(txtColor);
        txt9.setBackground(null);
        txt9.setPadding(padding, padding, padding, padding);
        addView(txt9, 8);

        CenterTextView txt10 = new CenterTextView(mContext);
        txt10.setText("10");
        txt10.setTextSize(txtSizeSmall);
        txt10.setTextColor(txtColor);
        txt10.setBackground(null);
        txt10.setPadding(padding, padding, padding, padding);
        addView(txt10, 9);

        CenterTextView txt11 = new CenterTextView(mContext);
        txt11.setText("11");
        txt11.setTextSize(txtSizeSmall);
        txt11.setTextColor(txtColor);
        txt11.setBackground(null);
        txt11.setPadding(padding, padding, padding, padding);
        addView(txt11, 10);

        CenterTextView txt12 = new CenterTextView(mContext);
        txt12.setText("12");
        txt12.setTextSize(txtSizeSmall);
        txt12.setTextColor(txtColor);
        txt12.setBackground(null);
        txt12.setPadding(padding, padding, padding, padding);
        addView(txt12, 11);
        
        ClockHandView handSecond = new ClockHandView(mContext);
        handSecond.run();
        addView(handSecond, 12);
        
        ClockHandView handMinute = new ClockHandView(mContext);
        handMinute.run();
        addView(handMinute, 13);
        
        ClockHandView handHour = new ClockHandView(mContext);
        handHour.run();
        addView(handHour, 14);
        
        CenterTextView txtLogo = new CenterTextView(mContext);
        // txtLogo.setText(R.string.app_name);
        txtLogo.setText(mContext.getString(R.string.app_name));
        txtLogo.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_dark));
        txtLogo.setTextSize(txtSizeBig);
        addView(txtLogo, 15);
        
        // setBackgroundColor(0xFFFFFFFF);
        // setBackground(null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取该ViewGroup的实际长和宽
        // 涉及到MeasureSpec类的使用
        int specSizeWidht = MeasureSpec.getSize(widthMeasureSpec);
        int specSizeHeigth = MeasureSpec.getSize(heightMeasureSpec);
        // 设置本ViewGroup的宽高
        int minSize = specSizeWidht < specSizeHeigth ? specSizeWidht : specSizeHeigth;
        showLog("minSize --> " + minSize);
        
        setMeasuredDimension(minSize, minSize);
        
        // getChildAt(12).measure(minSize, minSize);
        // getChildAt(13).measure(minSize, minSize);
        
    }
    
    /**
     * 获取表盘数字X坐标
     * 
     * @param centerX
     * @param radius
     * @param hour
     * @return
     */
    private double getChildX(double centerX, double radius, int hour) {
        double angle = 2 * Math.PI / 12 * hour;
        double sin = Math.sin(angle);
        return centerX + radius * sin;
    }

    /**
     * 获取表盘数字Y坐标
     * 
     * @param centerY
     * @param radius
     * @param hour
     * @return
     */
    private double getChildY(double centerY, double radius, int hour) {
        double angle = 2 * Math.PI / 12 * hour;
        double cos = Math.cos(angle);
        return centerY - radius * cos;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        
        showLog("changed --> " + changed + " l --> " + l + " t --> " + t + " r --> " + r + " b --> " + b);
        // 表盘宽度
        final double clockWidth = r - l;
        // 表盘高度
        final double clockHeight = b - t;
        // 表盘数字宽度
        final double childWidth = (clockWidth < clockHeight ? clockWidth : clockHeight) / 6;
        // 表盘数字高度
        final double childHeight = childWidth;
        // 半径
        final double radius = (clockWidth - childWidth) / 2;
        for (int i = 0; i < 12; i++) {
            View child = getChildAt(i);
            double childX = getChildX(clockWidth / 2, radius, i + 1);
            double childY = getChildY(clockHeight / 2, radius, i + 1);
            // 表盘数字左边界
            int childL = (int) (childX - childWidth / 2);
            // 表盘数字上边界
            int childT = (int) (childY - childHeight / 2);
            // 表盘数字右边界
            int childR = (int) (childX + childWidth / 2);
            // 表盘数字下边界
            int childB = (int) (childY + childHeight / 2);
            child.layout(childL, childT, childR, childB);
            // child.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_red_light));
            ((CenterTextView) child).setText(String.valueOf(i + 1));
            // ((CenterTextView) child).setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            ((CenterTextView) child).setTextAlign(CenterTextView.TEXT_ALIGN_CENTER_HORIZONTAL | CenterTextView.TEXT_ALIGN_CENTER_VERTICAL);
        }
        
        ClockHandView handSecond = (ClockHandView) getChildAt(12);
        handSecond.setCycle(60000);
        handSecond.setRadiusScale(1);
        handSecond.setStartAngle((float) getCurrentSSAngle());
        handSecond.setHandColor(0xff, 0xff, 0x00, 0x00);
        handSecond.layout((int) childWidth, (int) childHeight, (int) (clockWidth - childWidth), (int) (clockHeight - childHeight));
        
        ClockHandView handMinute = (ClockHandView) getChildAt(13);
        handMinute.setCycle(60000 * 60);
        handMinute.setRadiusScale(0.8f);
        handMinute.setStartAngle((float) getCurrentMMAngle());
        handMinute.setHandColor(0xff, 0x00, 0xff, 0x00);
        handMinute.layout((int) childWidth, (int) childHeight, (int) (clockWidth - childWidth), (int) (clockHeight - childHeight));
        
        ClockHandView handHour = (ClockHandView) getChildAt(14);
        handHour.setCycle(60000 * 60 * 12);
        handHour.setRadiusScale(0.6f);
        handHour.setStartAngle((float) getCurrentHHAngle());
        handHour.setHandColor(0xff, 0xff, 0xff, 0xff);
        handHour.layout((int) childWidth, (int) childHeight, (int) (clockWidth - childWidth), (int) (clockHeight - childHeight));
        
        View childLogo = getChildAt(15);
        int logoW = (int) clockWidth;
        int logoH = (int) clockHeight;
        childLogo.layout((int) (clockWidth - logoW) / 2, (int) (clockHeight - logoH) / 2, (int) (clockWidth - logoW) / 2 + logoW, (int) (clockHeight - logoH) / 2 + logoH);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    
    private void showLog(String log) {
        LogMgr.showLog(mContext, log, LogMgr.VERBOSE);
    }
    
    /**
     * 获取当前时针角度
     * 
     * @param timestamp
     * @return
     */
    private double getCurrentHHAngle() {
        String currentTime = mSimpleDateFormat.format(mDate);
        int hh = Integer.parseInt(currentTime.substring(0, 2));
        int mm = Integer.parseInt(currentTime.substring(3, 5));
        int ss = Integer.parseInt(currentTime.substring(6, 8));
        return 2 * Math.PI / 12 * hh + 2 * Math.PI / 12 / 60 * mm + 2 * Math.PI / 12 / 60 / 60 * ss;
    }
    
    /**
     * 获取当前分针角度
     * 
     * @return
     */
    private double getCurrentMMAngle() {
        String currentTime = mSimpleDateFormat.format(mDate);
        int mm = Integer.parseInt(currentTime.substring(3, 5));
        int ss = Integer.parseInt(currentTime.substring(6, 8));
        return 2 * Math.PI / 60 * mm + 2 * Math.PI / 60 / 60 * ss;
    }
    
    /**
     * 获取当前秒针角度
     * 
     * @return
     */
    private double getCurrentSSAngle() {
        String currentTime = mSimpleDateFormat.format(mDate);
        int ss = Integer.parseInt(currentTime.substring(6, 8));
        return 2 * Math.PI / 60 * ss;
    }

}
