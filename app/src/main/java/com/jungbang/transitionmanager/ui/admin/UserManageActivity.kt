package com.jungbang.transitionmanager.ui.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityManageUserBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class UserManageActivity: AppCompatActivity(),ComponentItemListener {
    lateinit var mBindng:ActivityManageUserBinding
    lateinit var mAdapter: UserManageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindng = DataBindingUtil.setContentView(this, R.layout.activity_manage_user)

        mAdapter = UserManageAdapter(this)
        mBindng.amuRvList.adapter = mAdapter
        mBindng.amuRvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}