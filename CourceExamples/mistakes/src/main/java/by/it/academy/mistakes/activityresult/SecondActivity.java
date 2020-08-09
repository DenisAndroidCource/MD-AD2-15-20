package by.it.academy.mistakes.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int result = 1 + 32;

//        FirstActivity.result = result;

        Intent intent = new Intent();
        intent.putExtra("RES", result);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
