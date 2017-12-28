package com.moggot.spyagent.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelfResponseModel {

    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCode() {
        return code;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
