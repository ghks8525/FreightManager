package com.jungbang.transitionmanager.ui.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ViewAdminUserBinding
import com.jungbang.transitionmanager.databinding.ViewHistoryBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class AdminMainAdapter(val mContext:Context): RecyclerView.Adapter<AdminMainAdapter.ViewHolder>() {

    private val mViewModel = (mContext as FragmentActivity).run {
        val viewModel: AdminMainViewmodel by viewModels()
        viewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminMainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewAdminUserBinding = DataBindingUtil.inflate(inflater, R.layout.view_admin_user, parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AdminMainAdapter.ViewHolder, position: Int) {
        val binding = holder.getBinding()
        binding.pos = position
        binding.listener = mContext as ComponentItemListener

        if(mViewModel.mldVehicles.value == null)
            return
        binding.data = mViewModel.mldVehicles.value!!.items[position]
    }

    override fun getItemCount(): Int = mViewModel.mldVehicles.value.let{ it?.items?.count() ?: 0 }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val vau_tv_name = itemView.findViewById<TextView>(R.id.vau_tv_name)
        val vau_tv_user_info = itemView.findViewById<TextView>(R.id.vau_tv_user_info)
        val vau_tv_status = itemView.findViewById<TextView>(R.id.vau_tv_status)
        val vau_tv_start_time = itemView.findViewById<TextView>(R.id.vau_tv_start_time)
        val vau_tv_start_info = itemView.findViewById<TextView>(R.id.vau_tv_start_info)
        val vau_tv_end_time = itemView.findViewById<TextView>(R.id.vau_tv_end_time)
        val vau_tv_end_info = itemView.findViewById<TextView>(R.id.vau_tv_end_info)


        fun getBinding() = DataBindingUtil.getBinding<ViewAdminUserBinding>(itemView) as ViewAdminUserBinding
    }
}