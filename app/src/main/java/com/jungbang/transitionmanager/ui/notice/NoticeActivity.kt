package com.jungbang.transitionmanager.ui.notice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityNoticeBinding
import com.jungbang.transitionmanager.ui.common.ActionBar
import com.jungbang.transitionmanager.ui.common.ComponentItemListener
import com.jungbang.transitionmanager.ui.common.VerticalItemDecorator

class NoticeActivity: AppCompatActivity(), ComponentItemListener, ActionBar.onActionBarListener {
    lateinit var mBinding: ActivityNoticeBinding
    lateinit var mAdapter: NoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice)
        mAdapter= NoticeAdapter(this)
        mBinding.anRvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.anRvList.setHasFixedSize(true)
        mBinding.anRvList.addItemDecoration(VerticalItemDecorator(20))
        mBinding.anRvList.adapter = mAdapter

        initActionBar()
    }

    private fun initActionBar() {
        val actionBar = ActionBar(this)

        actionBar.title = getString(R.string.notification)

        actionBar.setButton(ActionBar.ACTION_BACK, ActionBar.ACTION_CREATE)
        mBinding.actionbar = actionBar
    }

    override fun onClick(v: View, pos: Int) {
        if(v.findViewById<TextView>(R.id.vn_tv_msg).visibility == View.GONE) {
            v.findViewById<TextView>(R.id.vn_tv_msg).visibility = View.VISIBLE
            mAdapter.notifyItemChanged(pos)
        } else {
            v.findViewById<TextView>(R.id.vn_tv_msg).visibility = View.GONE
            v.findViewById<ImageView>(R.id.vn_iv_arrow).isSelected = false
        }
    }

    override fun onLeft() {
        finish()
    }

    override fun onRight() {
        startActivity(Intent(this, NoticeCreateActivity::class.java))
    }
}