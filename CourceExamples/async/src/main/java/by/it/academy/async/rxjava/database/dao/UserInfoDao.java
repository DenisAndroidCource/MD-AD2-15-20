package by.it.academy.async.rxjava.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import by.it.academy.async.rxjava.database.data.UserInfoEntity;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserInfoDao {
    @Insert(onConflict = REPLACE)
    long insert(UserInfoEntity userInfoEntity);

    @Query("SELECT * FROM UserInfo")
    List<UserInfoEntity> getAll();

    @Query("SELECT * FROM UserInfo WHERE id = :identity")
    List<UserInfoEntity> getAll(long identity);
}
