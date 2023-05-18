package com.example.foregroundservice

import android.R.attr.button
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class MainActivity : AppCompatActivity() {

    companion object {
        val channelId = "MyChannel"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener(View.OnClickListener { addNotification() })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNotification() {
        createNotificationChannel()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.run)
            .setContentTitle("My Notification")
            .setContentText("This is a notification")

       manager.notify(1, builder.build())
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                channelId,
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

    }
//    fun isServiceRunning(serviceClass: Class<*>): Boolean {
//        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        val runningServices = manager.getRunningServices(Integer.MAX_VALUE)
//
//        for (service in runningServices) {
//            if (serviceClass.name == service.service.className) {
//                return true
//            }
//        }
//        return false
//    }
}