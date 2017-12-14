package com.tmsnith.emotionsense.RequestModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ndodwaria on 4/2/17.
 */

public class ImageUrlModel {

    @SerializedName("url")
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
