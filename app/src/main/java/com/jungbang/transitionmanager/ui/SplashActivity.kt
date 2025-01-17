package com.jungbang.transitionmanager.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.sys.config.Config
import com.jungbang.transitionmanager.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {
    lateinit var mBinding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        
        if(Config.SUPPORT_DEBUG){
            startActivity(Intent(this, IndexActivity::class.java))
        }
    }
}