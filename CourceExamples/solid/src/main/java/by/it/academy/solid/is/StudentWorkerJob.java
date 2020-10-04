package by.it.academy.solid.is;

import android.util.Log;

public class StudentWorkerJob implements StudentActions, WorkerActions {

    @Override
    public void actionWork() {
        Log.d("DaylyJob", "DO JOB");
    }

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
