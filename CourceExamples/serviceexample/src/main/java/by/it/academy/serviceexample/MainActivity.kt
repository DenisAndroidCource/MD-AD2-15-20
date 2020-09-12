package by.it.academy.serviceexample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var service: ServiceExample? = null

    private val number by lazy { 20 }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName, binder: IBinder) {
            val serviceBinder = binder as ServiceExample.SimpleServiceBinder
            service = serviceBinder.getService()
        }

        override fun onServiceDisconnected(p0: ComponentName) {
            service = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startService(Intent(this@MainActivity, ServiceExample::class.java))
        }

        buttonStop.setOnClickListener { stopService(Intent(this@MainActivity, ServiceExample::class.java)) }

        val intent = Intent(this@MainActivity, ServiceExample::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        number.toDouble()
    }
}
