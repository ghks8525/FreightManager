package com.jungbang.transitionmanager.ui.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ViewHistoryBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class AdminHistoryAdapter(): RecyclerView.Adapter<AdminHistoryAdapter.ViewHolder>() {

    lateinit var mContext: Context

    constructor(context: Context) : this() {
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminHistoryAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.view_history, parent, false)


        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AdminHistoryAdapter.ViewHolder, position: Int) {
        val binding = holder.getBinding()
        binding.pos = position
        binding.listener = mContext as ComponentItemListener

    }

    override fun getItemCount(): Int = 10

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val vh_tv_driver_name = itemView.findViewById<TextView>(R.id.vh_tv_driver_name)
        val vh_tv_car_num = itemView.findViewById<TextView>(R.id.vh_tv_car_num)
        val vh_tv_start_time = itemView.findViewById<TextView>(R.id.vh_tv_start_time)
        val vh_tv_end_time = itemView.findViewById<TextView>(R.id.vh_tv_end_time)


        fun getBinding() = DataBindingUtil.getBinding<ViewHistoryBinding>(itemView) as ViewHistoryBinding
    }
}