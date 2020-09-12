package by.it.academy.serviceexample

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.concurrent.TimeUnit

private const val LOG = "LOG"

class ServiceExample : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotification(baseContext)
        Log.d(LOG, "onStartCommand $startId")
        someJob(startId)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(LOG, "onDestroy")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder = SimpleServiceBinder()

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    private fun someJob(startId: Int) {
        val runnable = Runnable {
            Log.d(LOG, "Started $startId")
            TimeUnit.SECONDS.sleep(5)
            Log.d(LOG, "After sleep $startId")
            stopSelf(startId)
        }
        Thread(runnable).start()
    }

    private fun createNotification(context: Context) {
        val notification = NotificationCompat.Builder(context, "CHANNEL")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("SERVICE IS WORKING")
                .setContentText("SERVICE IS WORKING")
                .build()
        startForeground(10, notification)
    }

    inner class SimpleServiceBinder : Binder() {
        fun getService() = this@ServiceExample
    }
}

