package com.mahmoudashraf.geideaapp.presentation.userdetails.view.services

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log

class CountDownTimerService : Service() {

    var countDownTimer: CountDownTimer? = null
    val timerIntent : Intent = Intent("com.geidea.timer_receiver")
    var counter = 5

    override fun onCreate() {
        super.onCreate()
        countDownTimer = object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e("thick",""+counter)
                timerIntent.putExtra("countdown", counter)
                if (counter==1)
                    timerIntent.putExtra("finish", true)
                counter -= 1
                sendBroadcast(timerIntent)
            }

            override fun onFinish() {
            }
        }
        countDownTimer?.start()
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
       return null
    }
}