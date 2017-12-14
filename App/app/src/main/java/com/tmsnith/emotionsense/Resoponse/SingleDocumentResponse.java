package com.tmsnith.emotionsense.Resoponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tms on 2/4/17.
 */

public class SingleDocumentResponse {
    @SerializedName("score")
    public double score;

    @SerializedName("id")
    public String id;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
