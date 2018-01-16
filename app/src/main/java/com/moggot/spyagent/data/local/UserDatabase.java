package com.moggot.spyagent.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.moggot.spyagent.data.model.SelfModel;

@Database(entities = {SelfModel.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase{

    public abstract UserDao userDao();
}
