package by.it.academy.contentresolver;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String URI_PATH = "content://by.it.academy.contentprovider/data/data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(Uri.parse(URI_PATH), null, null, null, null);
        if (cursor != null) {
            int descriptionInd = cursor.getColumnIndex("description");
            while (cursor.moveToNext()) {
                Log.d("Main", cursor.getString(descriptionInd));
            }
            cursor.close();
        }


    }
}
