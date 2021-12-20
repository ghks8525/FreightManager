package com.jungbang.transitionmanager.ui.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ViewHistoryBinding
import com.jungbang.transitionmanager.databinding.ViewUserManageBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class UserManageAdapter(private val mContext: Context): RecyclerView.Adapter<UserManageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewUserManageBinding = DataBindingUtil.inflate(inflater, R.layout.view_user_manage, parent, false)


        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding()
        binding.pos = position
        binding.listener = mContext as ComponentItemListener
    }

    override fun getItemCount(): Int = 10

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vum_tv_driver_name = itemView.findViewById<TextView>(R.id.vum_tv_driver_name)
        var vum_tv_contract = itemView.findViewById<TextView>(R.id.vum_tv_contract)
        var vum_tv_complete = itemView.findViewById<TextView>(R.id.vum_tv_complete)
        var vum_tv_date = itemView.findViewById<TextView>(R.id.vum_tv_date)

        fun getBinding() = DataBindingUtil.getBinding<ViewUserManageBinding>(itemView) as ViewUserManageBinding
    }
}