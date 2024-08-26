package com.shahnoza.broadcastreceiver

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shahnoza.broadcastreceiver.databinding.ActivityMainBinding
import com.shahnoza.broadcastreceiver.utils.MyReceiver

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myBroadcastReceiver: MyBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        myBroadcastReceiver=MyBroadcastReceiver()
        val intentFilter=IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(myBroadcastReceiver,intentFilter)
        binding.btnAlarm.setOnClickListener {
            val time = SystemClock.elapsedRealtime()
            val intent = Intent(this,MyReceiver::class.java)
            val pending = PendingIntent.getBroadcast(this,1,intent,PendingIntent.FLAG_MUTABLE)
             val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,time,10000,pending)

        }


    }
// dasturdan chiqqanimizdan so'ng budilnikni to'xtatish
    override fun onDestroy() {
        super. onDestroy()
        unregisterReceiver(myBroadcastReceiver)

    }

}
