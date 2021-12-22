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
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ViewUserManageBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class UserManageAdapter(private val mContext: Context): RecyclerView.Adapter<UserManageAdapter.ViewHolder>() {

    private val mViewModel = (mContext as FragmentActivity).run {
        val viewModel: UserManageViewmodel by viewModels()
        viewModel
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewUserManageBinding = DataBindingUtil.inflate(inflater, R.layout.view_user_manage, parent, false)


        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding()
        binding.pos = position
        binding.listener = mContext as ComponentItemListener

        if(mViewModel.mldUsers.value == null)
            return
        binding.data = mViewModel.mldUsers.value!!.Items[position]
    }

    override fun getItemCount(): Int = let{ if(mViewModel.mldUsers.value == null) 0 else mViewModel.mldUsers.value!!.Items.count()}

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vum_tv_driver_name = itemView.findViewById<TextView>(R.id.vum_tv_driver_name)
        var vum_tv_contract = itemView.findViewById<TextView>(R.id.vum_tv_contract)
        var vum_tv_date = itemView.findViewById<TextView>(R.id.vum_tv_date)

        fun getBinding() = DataBindingUtil.getBinding<ViewUserManageBinding>(itemView) as ViewUserManageBinding
    }
}