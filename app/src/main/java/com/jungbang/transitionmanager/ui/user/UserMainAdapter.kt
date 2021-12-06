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

class UserMainAdapter() : RecyclerView.Adapter<UserMainAdapter.ViewHolder>() {

    lateinit var mContext: Context

    constructor(context: Context) : this() {
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_user_inform, parent, false)
        Trace.debug("aaaaaaaaaaaaaaa")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 3

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var vui_iv_car:ImageView? = itemView.findViewById(R.id.vui_iv_car)
        var vui_tv_car_name:TextView? = itemView.findViewById(R.id.vui_tv_car_name)
        var vui_tv_num:TextView? = itemView.findViewById(R.id.vui_tv_num)
        var vui_tv_start_point:TextView? = itemView.findViewById(R.id.vui_tv_start_point)
        var vui_tv_destination:TextView? = itemView.findViewById(R.id.vui_tv_destination)



        fun Holder(itemView: View) {
        }

        fun onClick(view: View?) {}
    }
}