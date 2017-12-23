package com.moggot.spyagent.data.repository.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.moggot.spyagent.data.local.UserModel;

public class PreferenceRepo {

    private final static String KEY_USER_ID = "user_id";
    private final static String KEY_ACCESS_TOKEN = "access_token";

    @NonNull
    private SharedPreferences sharedPreferences;

    public PreferenceRepo(@NonNull SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveUser(@NonNull UserModel userModel) {
        long userid = userModel.getUserId();
        sharedPreferences.edit().putLong(KEY_USER_ID, userid).apply();
        String accessToken = userModel.getAccessToken();
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    public UserModel getUser() {
        long userId = sharedPreferences.getLong(KEY_USER_ID, 1);
        String accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, "");
        return new UserModel(userId, accessToken);
    }
}
