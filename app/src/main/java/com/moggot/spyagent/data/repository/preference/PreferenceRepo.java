package com.moggot.spyagent.data.repository.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.moggot.spyagent.data.model.SelfModel;

public class PreferenceRepo {

    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    @NonNull
    private SharedPreferences sharedPreferences;

    public PreferenceRepo(@NonNull SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveUser(@NonNull SelfModel self) {
        long userid = self.getUserId();
        sharedPreferences.edit().putLong(KEY_USER_ID, userid).apply();
        String accessToken = self.getAccessToken();
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    public SelfModel getUser() {
        long userId = sharedPreferences.getLong(KEY_USER_ID, 1);
        String accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, "");
        return new SelfModel(userId, accessToken);
    }
}
