package com.tmsnith.emotionsense.Resoponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ndodwaria on 4/2/17.
 */

public class faceRectangleModel {
    @SerializedName("height")
    public int height;
    @SerializedName("left")
    public int left;
    @SerializedName("top")
    public int top;
    @SerializedName("width")
    public int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
