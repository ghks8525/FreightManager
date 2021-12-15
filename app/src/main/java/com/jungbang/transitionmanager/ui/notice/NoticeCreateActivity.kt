package com.jungbang.transitionmanager.ui.notice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityNoticeCreateBinding
import com.jungbang.transitionmanager.ui.common.ActionBar

class NoticeCreateActivity:AppCompatActivity(), ActionBar.onActionBarListener {
    lateinit var mBinding:ActivityNoticeCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice_create)

        initActionBar()
    }

    private fun initActionBar(){
        val actionBar = ActionBar(this)
        actionBar.title = getString(R.string.noti_write)
        actionBar.setButton(ActionBar.ACTION_BACK, ActionBar.ACTION_NONE)
        mBinding.actionbar = actionBar
    }

    override fun onLeft() {
        finish()
    }

    override fun onRight() {}
}