package by.it.academy.async;

import android.os.Handler;
import android.os.HandlerThread;

public class JobHandler extends HandlerThread {

    private Handler handler;

    public JobHandler() {
        super("name");
    }

    public void post(Runnable runnable) {
        handler.post(runnable);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        handler = new Handler(getLooper());
    }
}
