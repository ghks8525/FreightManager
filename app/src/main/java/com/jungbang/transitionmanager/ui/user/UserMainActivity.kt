package com.jungbang.transitionmanager.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungbang.transitionmanager.databinding.ActivityUserMainBinding
import com.jungbang.transitionmanager.ui.common.VerticalItemDecorator
import androidx.fragment.app.FragmentTransaction
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.ui.CalendarFragment
import com.jungbang.transitionmanager.ui.common.ComponentItemListener
import com.jungbang.transitionmanager.ui.notice.NoticeActivity


class UserMainActivity:AppCompatActivity(), ComponentItemListener {
    lateinit var mBinding:ActivityUserMainBinding
    lateinit var mAdapter: UserMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_main)
        mBinding.listener = this

        mAdapter = UserMainAdapter(this)
        mBinding.aumRvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.aumRvList.setHasFixedSize(true)
        mBinding.aumRvList.addItemDecoration(VerticalItemDecorator(20))
        mBinding.aumRvList.adapter = mAdapter

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val calendarFragment = CalendarFragment(this)

        transaction.replace(mBinding.aumFlCalendar.id, calendarFragment) //layout, 교체될 layout

        transaction.commit() //commit으로 저장 하지 않으면 화면 전환이 되지 않음

    }

    fun onClick(v: View){
        when(v.id){
            R.id.aum_iv_alarm -> {
                startActivity(Intent(this, NoticeActivity::class.java))
            }
        }
    }
}