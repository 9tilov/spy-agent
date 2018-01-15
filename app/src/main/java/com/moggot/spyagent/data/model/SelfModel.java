package com.moggot.spyagent.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value = {"userId"},
        unique = true)})
public class SelfModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private long userId;

    private String accessToken;

    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("sex")
    @Expose
    private int sex;
    @SerializedName("bdate")
    @Expose
    private String bdate;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("subscriptions")
    @Expose
    private int subscriptions;
    @SerializedName("friends")
    @Expose
    private int friends;
    @SerializedName("code")
    @Expose
    private int code;

    public SelfModel(long userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getSubscriptions() {
        return subscriptions;
    }

    public int getCode() {
        return code;
    }

    public String getBdate() {
        return bdate;
    }

    public int getSex() {
        return sex;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getFriends() {
        return friends;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setSubscriptions(int subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SelfModel{" +
                "userId=" + userId +
                ", accessToken='" + accessToken + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", bdate='" + bdate + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", subscriptions=" + subscriptions +
                ", friends=" + friends +
                ", code=" + code +
                '}';
    }
}
