package com.tmsnith.emotionsense.Resoponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 11/12/17.
 */

public class FileUploadResponseModel {
    @SerializedName("pdfUrl")
    public String pdfUrl;

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
