package com.jungbang.transitionmanager.ui.notice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ViewNoticeBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class NoticeAdapter() : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    lateinit var mContext: Context

    constructor(context: Context) : this() {
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewNoticeBinding = DataBindingUtil.inflate(inflater, R.layout.view_notice, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val binding = holder.getBinding()
        binding.listener = mContext as ComponentItemListener
        binding.pos = position
    }

    override fun getItemCount(): Int = 70

    override fun getItemId(position: Int): Long {
        return position.hashCode().toLong()
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var vn_tv_title = v.findViewById<TextView>(R.id.vn_tv_title)
        var vn_tv_date = v.findViewById<TextView>(R.id.vn_tv_date)
        var vn_tv_msg = v.findViewById<TextView>(R.id.vn_tv_msg)
        var vn_iv_arrow = v.findViewById<ImageView>(R.id.vn_iv_arrow)

        fun getBinding() = DataBindingUtil.getBinding<ViewNoticeBinding>(itemView) as ViewNoticeBinding

        fun setListener(){
            getBinding().listener = mContext as ComponentItemListener
        }
    }
}
