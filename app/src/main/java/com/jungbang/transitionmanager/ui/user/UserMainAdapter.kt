package com.jungbang.transitionmanager.ui.user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.ui.common.SingleDialog

class UserMainAdapter() : RecyclerView.Adapter<UserMainAdapter.ViewHolder>() {

    lateinit var mContext: Context

    constructor(context: Context) : this() {
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_user_inform, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 3

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val vui_car_name = itemView.findViewById<TextView>(R.id.vui_car_name)
        val vui_tv_car_num = itemView.findViewById<TextView>(R.id.vui_tv_car_num)
        val vui_tv_start_time = itemView.findViewById<TextView>(R.id.vui_tv_start_time)
        val vui_tv_start_address = itemView.findViewById<TextView>(R.id.vui_tv_start_address)
        val vui_tv_end_time = itemView.findViewById<TextView>(R.id.vui_tv_end_time)
        val vui_tv_end_address = itemView.findViewById<TextView>(R.id.vui_tv_end_address)

        fun Holder(itemView: View) {
        }

        fun onClick(view: View?) {}
    }
}