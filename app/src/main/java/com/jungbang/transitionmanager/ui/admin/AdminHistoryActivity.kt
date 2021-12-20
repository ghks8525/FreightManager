package com.jungbang.transitionmanager.ui.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityAdminHistoryBinding
import com.jungbang.transitionmanager.ui.checkcar.UserAdapter
import com.jungbang.transitionmanager.ui.common.ActionBar
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class AdminHistoryActivity: AppCompatActivity(), ComponentItemListener, ActionBar.onActionBarListener {
    lateinit var mBinding:ActivityAdminHistoryBinding
    lateinit var mAdapter: AdminHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_admin_history)

        mAdapter = AdminHistoryAdapter(this)
        mBinding.aahRvList.adapter = mAdapter
        mBinding.aahRvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.aahRvList.setHasFixedSize(true)

        initActionBar()
    }

    private fun initActionBar() {
        val actionBar = ActionBar(this)

        actionBar.title = getString(R.string.history_title)

        actionBar.setButton(ActionBar.ACTION_BACK, ActionBar.ACTION_NONE)
        mBinding.actionbar = actionBar
    }

    override fun onLeft() {
        finish()
    }

    override fun onRight() {}
}