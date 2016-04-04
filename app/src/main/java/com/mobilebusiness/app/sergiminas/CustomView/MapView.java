package com.mobilebusiness.app.sergiminas.CustomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mobilebusiness.app.sergiminas.Helper.DeviceDimensionsHelper;
import com.mobilebusiness.app.sergiminas.R;

/**
 * Created by sergism2 on 04/04/2016.
 */
public class MapView extends View {
    private float mWidth;
    private float mHeigth;
    public static final int SCREEN_NUMBER = 5;
    /**
     * 10 steps
     * origen 0, 100
     * P1: 20, 80
     * P2: 50, 85
     * P3: 75, 78
     * P4: 90, 70
     * P5: 78, 64
     * P6: 54, 60
     * P7: 20, 68
     * P8: 10, 40
     * P9: 30, 35
     * P10: 60, 20
     * Final: 90 ,100
     */
    private Paint paint;

    private Bitmap bm;
    private int bm_offsetX, bm_offsetY;

    private Path animPath;
    private Path mPath;
    private PathMeasure pathMeasure;
    private float pathLength;

    private float step;   //distance each step
    private float distance;  //distance moved
    private float curX, curY;
    private boolean inc = false;
    private float curAngle;  //current angle
    private float targetAngle; //target angle
    private float stepAngle; //angle each step

    private float[] pos;
    private float[] tan;

    private Matrix matrix;

    private Path touchPath;
    public MapView(Context context) {
        super(context);
        initMapView();
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMapView();
    }

    public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMapView();
    }

    private void initMapView(){
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);

        bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bm_offsetX = bm.getWidth()/2;
        bm_offsetY = bm.getHeight()/2;

        animPath = new Path();

        pos = new float[2];
        tan = new float[2];

        matrix = new Matrix();

        touchPath = new Path();
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigth = h / 5;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(DeviceDimensionsHelper.getDisplayWidth(getContext()),
                DeviceDimensionsHelper.getDisplayHeight(getContext()) * SCREEN_NUMBER);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setAntiAlias(true);
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(3);
//        mPath.moveTo(50, 50);
//        mPath.cubicTo(300, 50, 100, 400, (float) (mWidth * 0.9), (float) (mHeigth * 0.9));
//        canvas.drawPath(mPath, paint);
//
//        mPath.reset();
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(20);
        float radius = 50.0f;

        CornerPathEffect cornerPathEffect =
                new CornerPathEffect(radius);

        paint.setPathEffect(cornerPathEffect);

        mPath.moveTo(mWidth * 0, mHeigth + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.80 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.50, mHeigth * (float) 0.85 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.75, mHeigth * (float) 0.78 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * (float) 0.70 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.78, mHeigth * (float) 0.64 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.54, mHeigth * (float) 0.60 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.68 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.10, mHeigth * (float) 0.40 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.30, mHeigth * (float) 0.35 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.60, mHeigth * (float) 0.20 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * 0 + mHeigth * 4);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.80 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.50, mHeigth * (float) 0.85 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.75, mHeigth * (float) 0.78 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * (float) 0.70 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.78, mHeigth * (float) 0.64 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.54, mHeigth * (float) 0.60 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.68 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.10, mHeigth * (float) 0.40 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.30, mHeigth * (float) 0.35 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.60, mHeigth * (float) 0.20 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * 0 + mHeigth * 3);
        canvas.drawPath(mPath, paint);
        mPath.reset();
        mPath.moveTo(mWidth * (float) 0.90, mHeigth * 0 + mHeigth * 3);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.80 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.50, mHeigth * (float) 0.85 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.75, mHeigth * (float) 0.78 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * (float) 0.70 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.78, mHeigth * (float) 0.64 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.54, mHeigth * (float) 0.60 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.68 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.10, mHeigth * (float) 0.40 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.30, mHeigth * (float) 0.35 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.60, mHeigth * (float) 0.20 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * 0 + mHeigth * 2);
        canvas.drawPath(mPath, paint);
        mPath.reset();
        mPath.moveTo(mWidth * (float) 0.90, mHeigth * 0 + mHeigth * 2);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.80 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.50, mHeigth * (float) 0.85 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.75, mHeigth * (float) 0.78 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * (float) 0.70 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.78, mHeigth * (float) 0.64 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.54, mHeigth * (float) 0.60 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.68 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.10, mHeigth * (float) 0.40 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.30, mHeigth * (float) 0.35 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.60, mHeigth * (float) 0.20 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * 0 + mHeigth);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.80);
        mPath.lineTo(mWidth * (float) 0.50, mHeigth * (float) 0.85);
        mPath.lineTo(mWidth * (float) 0.75, mHeigth * (float) 0.78);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * (float) 0.70);
        mPath.lineTo(mWidth * (float) 0.78, mHeigth * (float) 0.64);
        mPath.lineTo(mWidth * (float) 0.54, mHeigth * (float) 0.60);
        mPath.lineTo(mWidth * (float) 0.20, mHeigth * (float) 0.68);
        mPath.lineTo(mWidth * (float) 0.10, mHeigth * (float) 0.40);
        mPath.lineTo(mWidth * (float) 0.30, mHeigth * (float) 0.35);
        mPath.lineTo(mWidth * (float) 0.60, mHeigth * (float) 0.20);
        mPath.lineTo(mWidth * (float) 0.90, mHeigth * 0);


        canvas.drawPath(mPath, paint);
//        mPath.reset();
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(20);
//        mPath.moveTo(50, 100);
//        mPath.lineTo(1000, 4000);
//
//        mPath.reset();
//        paint.setColor(Color.GREEN);
//        paint.setStrokeWidth(20);
//        mPath.moveTo(50, 100);
//        mPath.lineTo(1000, 4000);
//        canvas.drawPath(mPath, paint);
       /* if(animPath.isEmpty()){
            return;
        }

        canvas.drawPath(animPath, paint);

        matrix.reset();
        if (distance < pathLength) {
            pathMeasure.getPosTan(distance, pos, tan);
            curX = pos[0]-bm_offsetX;
            curY = (float) (pos[1]- bm_offsetY * 2.5);
            matrix.postTranslate(curX, curY);

            canvas.drawBitmap(bm, matrix, null);

            distance += step;

            invalidate();
        } else {
            if(!inc) {
                if (curY < pos[1]- bm_offsetY * 2) {
                    curY += 0.5;
                } else {
                    inc = true;
                }
            } else {
                if (curY > pos[1] - bm_offsetY * 2.5) {
                    curY -= 0.5;
                } else {
                    inc = false;
                }
            }
            matrix.postTranslate(curX, curY);
            canvas.drawBitmap(bm, matrix, null);
            invalidate();
        }*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                touchPath.reset();
                touchPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                touchPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                touchPath.lineTo(event.getX(), event.getY());
                animPath = new Path(touchPath);

                pathMeasure = new PathMeasure(animPath, false);
                pathLength = pathMeasure.getLength();

                step = 1;
                distance = 0;
                curX = 0;
                curY = 0;

                stepAngle = 1;
                curAngle = 0;
                targetAngle = 0;

                /////invalidate();

                break;

        }

        return true;
    }
}
