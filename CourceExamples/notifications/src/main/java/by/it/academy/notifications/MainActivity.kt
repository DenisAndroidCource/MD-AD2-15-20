package by.it.academy.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        viewShowNotificationButton.setOnClickListener {
            val notification = createNotification()
            showNotification(1, notification)
        }
    }

    private fun createNotification() : Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 1, intent, 0)
        return NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(packageName.toString())
            .setContentIntent(pendingIntent)
            .build()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun showNotification(id: Int, notification: Notification) {
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(id, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel("CHANNEL_ID", "Default channel", NotificationManager.IMPORTANCE_DEFAULT)
                .apply { description = "Default notification channel description" }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
