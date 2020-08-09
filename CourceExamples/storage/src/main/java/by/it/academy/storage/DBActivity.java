package by.it.academy.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import by.it.academy.storage.db.DBHelper;

public class DBActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        dbHelper = new DBHelper(this);
        SQLiteDatabase databaseWrite = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", 11);
        contentValues.put("description", "Android");

        databaseWrite.insert("user", null, contentValues);

        String[] columns = new String[] {"name, description"};
        String selection = "description = ?";
        String[] args = new String[] {"Android"};

        List<Integer> names = new ArrayList<Integer>();
        Cursor cursor = databaseWrite.query("user", columns, selection, args, null, null, null);
        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex("name");

            while (cursor.moveToNext()) {
                Integer name = cursor.getInt(nameIndex);
                names.add(name);
            }

            cursor.close();
        }
    }
}
