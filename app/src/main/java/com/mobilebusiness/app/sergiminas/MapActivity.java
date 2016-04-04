package com.mobilebusiness.app.sergiminas;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.mobilebusiness.app.sergiminas.CustomView.MapView;

public class MapActivity extends AppCompatActivity {

    private ScrollView mScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //replace this line to scroll up or down
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 100L);
    }
}
