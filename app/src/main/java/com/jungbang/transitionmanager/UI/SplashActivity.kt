package com.jungbang.transitionmanager.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.Sys.Config.Config
import com.jungbang.transitionmanager.databinding.ActivitySplashBinding

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