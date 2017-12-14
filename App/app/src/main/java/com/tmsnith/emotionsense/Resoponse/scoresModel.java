package com.tmsnith.emotionsense.Resoponse;

import com.google.gson.annotations.SerializedName;
import com.tmsnith.emotionsense.RequestModels.DocumentModel;

/**
 * Created by ndodwaria on 4/2/17.
 */

public class scoresModel {
    @SerializedName("anger")
    public Double anger;
    @SerializedName("contempt")
    public Double contempt;
    @SerializedName("disgust")
    public Double disgust;
    @SerializedName("fear")
    public Double fear;
    @SerializedName("happiness")
    public Double happiness;
    @SerializedName("neutral")
    public Double neutral;
    @SerializedName("sadness")
    public Double sadness;
    @SerializedName("surprise")
    public Double surprise;

    public Double getAnger() {
        return anger;
    }

    public void setAnger(Double anger) {
        this.anger = anger;
    }

    public Double getContempt() {
        return contempt;
    }

    public void setContempt(Double contempt) {
        this.contempt = contempt;
    }

    public Double getDisgust() {
        return disgust;
    }

    public void setDisgust(Double disgust) {
        this.disgust = disgust;
    }

    public Double getFear() {
        return fear;
    }

    public void setFear(Double fear) {
        this.fear = fear;
    }

    public Double getHappiness() {
        return happiness;
    }

    public void setHappiness(Double happiness) {
        this.happiness = happiness;
    }

    public Double getNeutral() {
        return neutral;
    }

    public void setNeutral(Double neutral) {
        this.neutral = neutral;
    }

    public Double getSadness() {
        return sadness;
    }

    public void setSadness(Double sadness) {
        this.sadness = sadness;
    }

    public Double getSurprise() {
        return surprise;
    }

    public void setSurprise(Double surprise) {
        this.surprise = surprise;
    }
}

