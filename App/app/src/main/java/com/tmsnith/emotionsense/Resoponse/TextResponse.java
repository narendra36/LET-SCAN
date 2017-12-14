package com.tmsnith.emotionsense.Resoponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tms on 1/4/17.
 */

public class TextResponse {

 @SerializedName("documents")
    public ArrayList<SingleDocumentResponse> documents;

    @SerializedName("errors")
    public ArrayList<SingleError> errors;

    public ArrayList<SingleError> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<SingleError> errors) {
        this.errors = errors;
    }

    public ArrayList<SingleDocumentResponse> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<SingleDocumentResponse> documents) {
        this.documents = documents;
    }
}
