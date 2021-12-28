package com.jungbang.transitionmanager.ui.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityAdminMainBinding
import com.jungbang.transitionmanager.model.dto.Vehicles
import com.jungbang.transitionmanager.ui.common.ComponentItemListener
import com.jungbang.transitionmanager.ui.notice.NoticeActivity

class AdminMainActivity:AppCompatActivity(), ComponentItemListener {
    lateinit var mBinding:ActivityAdminMainBinding
    lateinit var mAdapter:AdminMainAdapter
    val mViewmodel: AdminMainViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_admin_main)
        mBinding.listener = this

        mViewmodel.mldVehicles.observe(this, this::onDataListChanged)
        mViewmodel.getVehicles()

        mAdapter = AdminMainAdapter(this)
        mBinding.aamRvList.adapter = mAdapter
        mBinding.aamRvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun onDataListChanged(data: Vehicles.Response.Data){
        mAdapter.notifyDataSetChanged()
    }

    fun onClick(v: View){
        when(v.id){
            R.id.aam_iv_alarm -> {
                startActivity(Intent(this, NoticeActivity::class.java))
            }

            R.id.aam_ll_plus -> {
                startActivity(Intent(this, AdminScheduleActivity::class.java))
            }
        }
    }
}