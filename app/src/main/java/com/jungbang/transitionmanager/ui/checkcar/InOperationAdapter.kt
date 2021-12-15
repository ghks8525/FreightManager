package com.jungbang.transitionmanager.ui.checkcar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ViewInOperationBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener

class InOperationAdapter(): RecyclerView.Adapter<InOperationAdapter.ViewHolder>() {
    lateinit var mContext: Context

    constructor(context: Context) : this() {
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewInOperationBinding = DataBindingUtil.inflate(inflater, R.layout.view_in_operation, parent, false)


        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding()
        binding.pos = position
        binding.listener = mContext as ComponentItemListener

        binding.vioTvCarNum.text = position.toString() + binding.vioTvCarNum.text

    }

    override fun getItemCount(): Int = 20

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vio_tv_name = itemView.findViewById<TextView>(R.id.vio_tv_name)
        val vio_tv_car_num = itemView.findViewById<TextView>(R.id.vio_tv_car_num)
        val vio_tv_car_name = itemView.findViewById<TextView>(R.id.vio_tv_car_name)
        val vio_iv_detail = itemView.findViewById<ImageView>(R.id.vio_iv_detail)


        fun getBinding() = DataBindingUtil.getBinding<ViewInOperationBinding>(itemView) as ViewInOperationBinding
    }
}
