package com.jungbang.transitionmanager.UI.User

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityUserMainBinding

class UserMainActivity:AppCompatActivity() {
    lateinit var mBinding:ActivityUserMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_main)
    }
}