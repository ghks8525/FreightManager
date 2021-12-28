package com.jungbang.transitionmanager.ui.notice

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityNoticeCreateBinding
import com.jungbang.transitionmanager.model.dto.CommonData
import com.jungbang.transitionmanager.model.dto.NoticeAdd
import com.jungbang.transitionmanager.model.dto.NoticeEdit
import com.jungbang.transitionmanager.ui.common.ActionBar

class NoticeCreateActivity : AppCompatActivity(), ActionBar.onActionBarListener {
    lateinit var mBinding: ActivityNoticeCreateBinding
    var id = 0
    var title: String? = ""
    var content: String? = ""

    val mViewModel: NoticeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice_create)
        mBinding.listener = this

        id = intent.getIntExtra("id", 0)
        title = intent.getStringExtra("title")
        content = intent.getStringExtra("content")

        if(!title.isNullOrEmpty()){
            mBinding.ancEtTitle.setText(title)
            mBinding.ancEtContent.setText(content)
        }

        initActionBar()
    }

    private fun initActionBar() {
        val actionBar = ActionBar(this)
        actionBar.title = getString(R.string.noti_write)
        actionBar.setButton(ActionBar.ACTION_BACK, ActionBar.ACTION_NONE)
        mBinding.actionbar = actionBar
    }

    override fun onLeft() {
        finish()
    }

    override fun onRight() {}

    fun onClick(v: View) {
        if(mBinding.ancEtTitle.text.isNullOrEmpty())
            return

        if (!title.isNullOrEmpty()) {
            mViewModel.editNotice(
                id,
                NoticeEdit.Request(
                    mBinding.ancEtTitle.text.toString(),
                    mBinding.ancEtContent.text.toString()
                )
            )
        } else {
            mViewModel.addNotice(
                NoticeAdd.Request(
                    mBinding.ancEtTitle.text.toString(),
                    mBinding.ancEtContent.text.toString(),
                    CommonData.userinfo!!.companyId
                )
            )
        }
        finish()
    }
}