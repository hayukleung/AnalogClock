package com.hayukleung.analogclock;

import com.hayukleung.analogclock.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * 展示{@link com.hayukleung.analogclock.AnalogClockView}
 * 
 * @author HayukLeung
 *
 */
public class ClockActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        // AnalogClockView analogClockView = (AnalogClockView) findViewById(R.id.ActivityHelloView$analog_clock_view);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
    }
}
