package com.jungbang.transitionmanager.ui.notice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.transitionmanager.BR
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ViewNoticeBinding
import com.jungbang.transitionmanager.model.dto.CommonData
import com.jungbang.transitionmanager.ui.admin.UserManageViewmodel
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class NoticeAdapter(val mContext:Context) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {
    private val mViewModel = (mContext as FragmentActivity).run {
        val viewModel: NoticeViewModel by viewModels()
        viewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewNoticeBinding = DataBindingUtil.inflate(inflater, R.layout.view_notice, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding()

        if (mViewModel.mldNotices.value == null)
            return

        binding.listener = mContext as ComponentItemListener
        binding.pos = position
        binding.data = mViewModel.mldNotices.value!!.Items[position]
        binding.isAmdin = CommonData.userinfo?.recruitType == "admin"
    }

    override fun getItemCount(): Int = mViewModel.mldNotices.value.let{ it?.Items?.count() ?: 0 }

    override fun getItemId(position: Int): Long {
        return position.hashCode().toLong()
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var vn_tv_title = v.findViewById<TextView>(R.id.vn_tv_title)
        var vn_tv_date = v.findViewById<TextView>(R.id.vn_tv_date)
        var vn_tv_msg = v.findViewById<TextView>(R.id.vn_tv_msg)
        var vn_iv_arrow = v.findViewById<ImageView>(R.id.vn_iv_arrow)
        var vn_tv_edit = v.findViewById<TextView>(R.id.vn_tv_edit)
        var vn_tv_delete = v.findViewById<TextView>(R.id.vn_tv_delete)

        fun getBinding() = DataBindingUtil.getBinding<ViewNoticeBinding>(itemView) as ViewNoticeBinding

        fun setListener(){
            getBinding().listener = mContext as ComponentItemListener
        }
    }
}
