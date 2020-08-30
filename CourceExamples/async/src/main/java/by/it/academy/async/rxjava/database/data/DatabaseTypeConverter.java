package by.it.academy.async.rxjava.database.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class DatabaseTypeConverter {

    @TypeConverter
    public Long dateToMilliseconds(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public Date millisecondsToDate(Long millis) {
        return new Date(millis);
    }

}
