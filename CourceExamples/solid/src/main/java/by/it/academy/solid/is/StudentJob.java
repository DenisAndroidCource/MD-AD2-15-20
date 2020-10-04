package by.it.academy.solid.is;

import android.util.Log;

public class StudentJob implements StudentActions {

    @Override
    public void actionStudy() {
        Log.d("DaylyJob", "Study");
    }

    @Override
    public void actionDraw() {
        Log.d("DaylyJob", "Draw");
    }

    @Override
    public void actionWrite() {
        Log.d("DaylyJob", "Write");
    }
}
