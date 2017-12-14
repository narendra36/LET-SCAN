package com.tmsnith.emotionsense.Resoponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tms on 2/4/17.
 */

public class SingleError {

    @SerializedName("id")
    public String id;

    @SerializedName("message")
    public String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
