package com.moggot.spyagent.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopOfLikes {

    @SerializedName("Users")
    @Expose
    private List<UserInfo> userInfos;

    @SerializedName("code")
    @Expose
    private int code;

    public int getCode() {
        return code;
    }

    public List<UserInfo> getUsersInfo() {
        return userInfos;
    }

    @Override
    public String toString() {
        return
                "TopOfLikes{" +
                        "code = '" + code + '\'' +
                        ",userInfos = '" + userInfos + '\'' +
                        "}";
    }
}