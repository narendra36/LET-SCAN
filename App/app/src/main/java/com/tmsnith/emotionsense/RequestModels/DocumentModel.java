package com.tmsnith.emotionsense.RequestModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tms on 1/4/17.
 */

public class DocumentModel {
  @SerializedName("documents")
  public ArrayList<SingleDocument> documents;

    public DocumentModel() {
      documents = new ArrayList<>();
    }

  public ArrayList<SingleDocument> getDocument() {
    return documents;
  }

  public void setDocument(ArrayList<SingleDocument> documents) {
    this.documents = documents;
  }
}

