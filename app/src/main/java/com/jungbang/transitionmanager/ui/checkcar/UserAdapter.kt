package com.jungbang.transitionmanager.ui.checkcar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ViewUserListBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class UserAdapter(): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    lateinit var mContext: Context

    constructor(context: Context) : this() {
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewUserListBinding = DataBindingUtil.inflate(inflater, R.layout.view_user_list, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding()
        binding.pos = position
        binding.listener = mContext as ComponentItemListener
    }

    override fun getItemCount(): Int = 10

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vul_tv_name = itemView.findViewById<TextView>(R.id.vul_tv_name)
        val vul_tv_car_num = itemView.findViewById<TextView>(R.id.vul_tv_car_num)

        fun getBinding() = DataBindingUtil.getBinding<ViewUserListBinding>(itemView) as ViewUserListBinding
    }
}
