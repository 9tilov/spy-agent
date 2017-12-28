package com.moggot.spyagent.data.network;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

public class CommonServerModel {

    @NonNull
    private PreferenceRepo preferenceRepo;
    private long userId;

    public CommonServerModel(@NonNull PreferenceRepo preferenceRepo) {
        this.preferenceRepo = preferenceRepo;
        userId = preferenceRepo.getUser().getUserId();
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
