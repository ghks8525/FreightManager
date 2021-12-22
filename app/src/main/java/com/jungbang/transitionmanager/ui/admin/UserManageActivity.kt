package com.jungbang.transitionmanager.ui.admin

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityManageUserBinding
import com.jungbang.transitionmanager.model.dto.Users
import com.jungbang.transitionmanager.ui.common.ActionBar
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class UserManageActivity: AppCompatActivity(),ComponentItemListener, ActionBar.onActionBarListener {
    lateinit var mBindng:ActivityManageUserBinding
    lateinit var mAdapter: UserManageAdapter
    lateinit var userAddDialog: UserAddDialog
    val mViewModel:UserManageViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        mBindng = DataBindingUtil.setContentView(this, R.layout.activity_manage_user)

        mAdapter = UserManageAdapter(this)
        mBindng.amuRvList.adapter = mAdapter
        mBindng.amuRvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mViewModel.mldUsers.observe(this, this::onDataListChanged)
        mViewModel.getUsers()
        initActionBar()
        super.onCreate(savedInstanceState)
    }

    fun initActionBar(){
        val actionBar = ActionBar(this)
        actionBar.setButton(ActionBar.ACTION_BACK, ActionBar.ACTION_ADD)
        actionBar.title = getString(R.string.manage_user)
        mBindng.actionbar = actionBar
    }

    override fun onLeft() {

    }

    override fun onRight() {
        userAddDialog = UserAddDialog(this)
        userAddDialog.show(supportFragmentManager, null)
    }

    fun onDataListChanged(data: Users.Response.Data){
        mAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View, pos: Int) {
        when(v.id){
            R.id.vum_cl_list -> {
                UserDialog(this, mViewModel.mldUsers.value!!.Items[pos]).show(supportFragmentManager,null)
            }
        }
    }
}