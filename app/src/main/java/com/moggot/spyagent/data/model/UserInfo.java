package com.moggot.spyagent.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    public UserInfo(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @SerializedName("bdate")
    @Expose
    private String bdate;

    @SerializedName("like_count")
    @Expose
    private int likeCount;

    @SerializedName("sex")
    @Expose
    private int sex;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("ID")
    @Expose
    private int iD;

    @SerializedName("photo_url")
    @Expose
    private String photoUrl;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    public String getBdate() {
        return bdate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getSex() {
        return sex;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return iD;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return
                "UserInfo{" +
                        "bdate = '" + bdate + '\'' +
                        ",like_count = '" + likeCount + '\'' +
                        ",sex = '" + sex + '\'' +
                        ",last_name = '" + lastName + '\'' +
                        ",iD = '" + iD + '\'' +
                        ",photo_url = '" + photoUrl + '\'' +
                        ",first_name = '" + firstName + '\'' +
                        "}";
    }
}