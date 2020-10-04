package by.it.academy.solid.is;

import android.util.Log;

public class WorkerJob implements WorkerActions {

    @Override
    public void actionWork() {
        Log.d("DaylyJob", "DO JOB");
    }
}
