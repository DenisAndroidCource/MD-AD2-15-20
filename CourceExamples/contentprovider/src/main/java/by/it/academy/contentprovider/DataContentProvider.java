package by.it.academy.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import by.it.academy.contentprovider.db.DBHelper;

//   content://by.it.academy.contentprovider/data/data

public class DataContentProvider extends ContentProvider {

    private static final String AUTHORITY = "by.it.academy.contentprovider";
    private static final int URI_USER_CODE = 1;
    private static final int URI_USER_ID_CODE = 2;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "data/data", URI_USER_CODE);
        uriMatcher.addURI(AUTHORITY, "data/data/#", URI_USER_ID_CODE);
    }

    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case URI_USER_CODE:
                cursor = database.query("user", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case URI_USER_ID_CODE:
                cursor = database.query("user", null, "id = ?",
                        new String[]{uri.getLastPathSegment()}, null, null, null);
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "by.it.academy.contentprovider/object";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (uriMatcher.match(uri) == URI_USER_CODE) {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            long id = database.insert("user", "", contentValues);
            return Uri.withAppendedPath(uri, String.valueOf(id));
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
