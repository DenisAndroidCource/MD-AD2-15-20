package by.it.academy.gittestmodule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private int value = 90;
    private GitTestClass gitTestClass = new GitTestClass("repo", "branch");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_layout);
        Log.d(LOG_TAG, "onCreate value=" + value);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("KEY_SAVED_VALUED", 15);
        outState.putInt("KEY_SAVED_VALUED_2", 15);
        outState.putInt("KEY_SAVED_VALUED_4", 15);
        outState.putSerializable("KEY_SERIALIZED_", gitTestClass);
        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        value = savedInstanceState.getInt("KEY_SAVED_VALUED", 0);

        Serializable serializable = savedInstanceState.getSerializable("KEY_SERIALIZED_");
        if (serializable instanceof  GitTestClass) {
            gitTestClass = (GitTestClass) serializable;
        }

        Log.d(LOG_TAG, "onRestoreInstanceState - savedValue=" + value);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
