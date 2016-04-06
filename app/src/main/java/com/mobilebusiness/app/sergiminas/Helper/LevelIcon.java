package com.mobilebusiness.app.sergiminas.Helper;

/**
 * Created by sergism2 on 06/04/2016.
 */
public class LevelIcon {
    private float xStart, xEnd, yStart, yEnd;
    public LevelIcon(float x, float width, float y, float heigth) {
        xStart = x;
        xEnd = x + width;
        yStart = y;
        yEnd = yStart + heigth;
    }

    public boolean isClicked(float x, float y) {
        if((x > xStart && x < xEnd) && (y > yStart && y < yEnd)) {
            return true;
        }
        return false;
    }
}
