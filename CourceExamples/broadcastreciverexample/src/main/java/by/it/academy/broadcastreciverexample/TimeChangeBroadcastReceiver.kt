package by.it.academy.broadcastreciverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TimeChangeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_TIMEZONE_CHANGED -> printLog("Time zone has been changed")
            Intent.ACTION_TIME_CHANGED -> printLog("Time has been changed")
            "by.it.academy.broadcastreciverexample.CUSTOM_ACTION" -> printLog(intent.getStringExtra("KEY")!!)
        }
    }

    private fun printLog(msg: String) {
        Log.d("TimeChangeBroadcast", msg)
    }
}