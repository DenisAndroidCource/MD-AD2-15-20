package by.it.academy.broadcastreciverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

private const val CUSTOM_ACTION = "by.it.academy.broadcastreciverexample.CUSTOM_ACTION"

class MainActivity : AppCompatActivity() {

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(this@MainActivity, "BroadcastReceiver", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createReceiver()
        button.setOnClickListener {
            sendBroadcast(Intent().apply {
                action = CUSTOM_ACTION
                putExtra("KEY", "CUSTOM ACTION HAS BEEN SENT")
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    private fun createReceiver() {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(CUSTOM_ACTION)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }
}
