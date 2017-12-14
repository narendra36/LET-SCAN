package com.tmsnith.emotionsense.Resoponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ndodwaria on 4/2/17.
 */

public class SingleFaceImageResponse {

    @SerializedName("faceRectangle")
    public faceRectangleModel faceRectangle;
    @SerializedName("scores")
    public scoresModel scores;

    public faceRectangleModel getFaceRectangle() {
        return faceRectangle;
    }

    public void setFaceRectangle(faceRectangleModel faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    public scoresModel getScores() {
        return scores;
    }

    public void setScores(scoresModel scores) {
        this.scores = scores;
    }
}
