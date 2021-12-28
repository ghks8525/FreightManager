package com.jungbang.transitionmanager.ui.notice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityNoticeBinding
import com.jungbang.transitionmanager.model.dto.CommonData
import com.jungbang.transitionmanager.model.dto.Notices
import com.jungbang.transitionmanager.sys.utils.Utils
import com.jungbang.transitionmanager.ui.common.ActionBar
import com.jungbang.transitionmanager.ui.common.ComponentItemListener
import com.jungbang.transitionmanager.ui.common.VerticalItemDecorator
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultCallback

import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult


class NoticeActivity : AppCompatActivity(), ComponentItemListener, ActionBar.onActionBarListener {
    lateinit var mBinding: ActivityNoticeBinding
    lateinit var mAdapter: NoticeAdapter
    val mViewModel: NoticeViewModel by viewModels()
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice)
        mAdapter = NoticeAdapter(this)
        mBinding.anRvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.anRvList.setHasFixedSize(true)
        mBinding.anRvList.addItemDecoration(VerticalItemDecorator(20))
        mBinding.anRvList.adapter = mAdapter

        mViewModel.mldNotices.observe(this, this::onDataListChanged)
        mViewModel.getNotice()

        resultLauncher = registerForActivityResult(
            StartActivityForResult()
        ) {
            mViewModel.getNotice()
        }

        initActionBar()
    }

    private fun initActionBar() {
        val actionBar = ActionBar(this)

        actionBar.title = getString(R.string.notification)

        actionBar.setButton(
            ActionBar.ACTION_BACK,
            let { if (CommonData.userinfo?.recruitType == "admin") ActionBar.ACTION_CREATE else ActionBar.ACTION_NONE })
        mBinding.actionbar = actionBar
    }

    fun onDataListChanged(data: Notices.Response.Data) {
        mAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View, pos: Int) {
        when (v.id) {
            R.id.vn_cl_root -> {
                mViewModel.mldNotices.value!!.Items[pos].selected =
                    !mViewModel.mldNotices.value!!.Items[pos].selected
                mAdapter.notifyItemChanged(pos)
            }

            R.id.vn_tv_edit -> {
                val intent = Intent(this, NoticeCreateActivity::class.java)
                intent.putExtra("id", mViewModel.mldNotices.value!!.Items[pos].id)
                intent.putExtra("title", mViewModel.mldNotices.value!!.Items[pos].title)
                intent.putExtra("content", mViewModel.mldNotices.value!!.Items[pos].content)
                resultLauncher.launch(intent)
            }

            R.id.vn_tv_delete -> {
                mViewModel.deleteNotice(mViewModel.mldNotices.value!!.Items[pos].id)
            }
        }
    }

    override fun onLeft() {
        finish()
    }

    override fun onRight() {
        resultLauncher.launch(Intent(this, NoticeCreateActivity::class.java))
    }
}