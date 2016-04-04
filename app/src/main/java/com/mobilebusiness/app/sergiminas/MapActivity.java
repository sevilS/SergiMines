package com.mobilebusiness.app.sergiminas;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.ViewGroup;

import com.mobilebusiness.app.sergiminas.CustomView.MapView;

public class MapActivity extends AppCompatActivity {

    MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.mapView);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mMapView.setWidth(size.x);
        mMapView.setHeigth(size.y);
    }
}
