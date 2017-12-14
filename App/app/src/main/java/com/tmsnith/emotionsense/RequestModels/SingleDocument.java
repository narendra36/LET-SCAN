package com.tmsnith.emotionsense.RequestModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tms on 2/4/17.
 */

public class SingleDocument {

    @SerializedName("id")
    String id;
    @SerializedName("text")
    String text;

    public SingleDocument(String id, String text)
    {
        this.id = id;
        this.text = text;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
