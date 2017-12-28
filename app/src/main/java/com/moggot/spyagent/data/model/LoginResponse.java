package com.moggot.spyagent.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("code")
    @Expose
    private int code;

    public int getCode() {
        return code;
    }
}
