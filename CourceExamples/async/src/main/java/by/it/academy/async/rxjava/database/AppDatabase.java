package by.it.academy.async.rxjava.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import by.it.academy.async.rxjava.database.dao.UserInfoDao;
import by.it.academy.async.rxjava.database.data.UserInfoEntity;

@Database(entities = {UserInfoEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "userinfodatabase")
                .build();
    }

    public abstract UserInfoDao getUserInfoDao();
}
