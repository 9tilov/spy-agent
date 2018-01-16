package com.moggot.spyagent.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.moggot.spyagent.data.model.SelfModel;

import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM SelfModel")
    Single<SelfModel> getSelfInfo();

    @Query("SELECT id FROM SelfModel WHERE userId = :userId")
    int getId(long userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCredentials(SelfModel selfModel);

    @Update
    void updateSelfInfo(SelfModel selfModel);
}
