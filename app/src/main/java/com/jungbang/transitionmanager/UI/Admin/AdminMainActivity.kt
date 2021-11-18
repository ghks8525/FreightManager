package com.jungbang.transitionmanager.UI.Admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityAdminMainBinding

class AdminMainActivity:AppCompatActivity() {
    lateinit var mBinding:ActivityAdminMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_admin_main)
    }
}