package com.moggot.spyagent.data.local;

public class UserModel {

    private long userId;
    private String accessToken;

    public UserModel(long userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;

    }

    public long getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
