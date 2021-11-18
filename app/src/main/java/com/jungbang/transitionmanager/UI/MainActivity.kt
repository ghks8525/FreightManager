package com.jungbang.transitionmanager.UI

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.tasks.OnCompleteListener
import com.jungbang.freightmanager.Utils.Trace
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.jungbang.transitionmanager.BackgroundLocationUpdateService
import com.jungbang.transitionmanager.FCMPushReceiver
import com.jungbang.transitionmanager.R


class MainActivity : AppCompatActivity() {
    val PERMISSION = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION)

    var mPushToken = ""
    var mTokenListener = OnCompleteListener<String> { task ->
        if (!task.isSuccessful) {
            Trace.debug(">> Fetching FCM registration token failed ${task.exception}")
            return@OnCompleteListener
        }
        // Get new FCM registration token
        mPushToken = task.result.toString()
        Trace.debug("aaaaaaaaaaaaaaaaaaaaaaa = $mPushToken")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FCMPushReceiver.getToken(mTokenListener)

        if(checkPermission()){
            Trace.debug("permission = ${checkPermission()}")
            startService(Intent(this, BackgroundLocationUpdateService::class.java))
        }else{
            Trace.debug("permission = ${checkPermission()}")
            ActivityCompat.requestPermissions(this, PERMISSION, 1)

        }
    }

    fun checkPermission(): Boolean {
        PERMISSION.forEach {
            if(ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(checkPermission()){
            startService(Intent(this, BackgroundLocationUpdateService::class.java))
        }
    }
}