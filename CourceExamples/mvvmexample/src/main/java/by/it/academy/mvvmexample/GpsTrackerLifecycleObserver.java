package by.it.academy.mvvmexample;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class GpsTrackerLifecycleObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void startTracking(){
        Log.d("GpsTrackerLifecycle", "Tracking started");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stopTracking() {
        Log.d("GpsTrackerLifecycle", "Tracking stopped");
    }
}
