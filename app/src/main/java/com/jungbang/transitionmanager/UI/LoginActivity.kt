package com.jungbang.transitionmanager.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityLoginBinding

class LoginActivity:AppCompatActivity() {
    lateinit var mBinding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }
}