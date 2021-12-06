package com.jungbang.transitionmanager.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityUserMainBinding
import com.jungbang.transitionmanager.ui.common.VerticalItemDecorator

class UserMainActivity:AppCompatActivity() {
    lateinit var mBinding:ActivityUserMainBinding
    lateinit var mAdapter: UserMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_main)

        mAdapter = UserMainAdapter(this)
        mBinding.aumRvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.aumRvList.setHasFixedSize(true)
        mBinding.aumRvList.addItemDecoration(VerticalItemDecorator(20))
        mBinding.aumRvList.adapter = mAdapter
    }
}