package by.it.academy.async.observer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements JavaObservable.JavaObservableAction {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JavaObservable.getInstance().subscribe(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JavaObservable.getInstance().unsubscribe(this);
    }

    @Override
    public void onDataChanged(String data) {

    }
}
