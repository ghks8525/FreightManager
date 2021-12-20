package com.jungbang.transitionmanager.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.FragmentCalendarBinding
import com.jungbang.transitionmanager.databinding.ViewCalendarBinding
import com.jungbang.transitionmanager.databinding.ViewNoticeBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener
import java.util.*

class CalendarFragment(private val mContext:Context): Fragment() {
    lateinit var mBinding: FragmentCalendarBinding
    var month: Int = 0
    var year: Int = 0
    var dayWeek: Int = 0
    var lastDay: Int = 0
    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View
    {
        mBinding = FragmentCalendarBinding.inflate(inflater, container, false)

        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        calendar.set(Calendar.YEAR ,year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DATE, 1)
        dayWeek = calendar.get(Calendar.DAY_OF_WEEK)
        lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        mBinding.fcTvDate.text = "%s년 %s월".format(year, month + 1)


        mBinding.fcRvList.adapter = CalendarAdapter()
        mBinding.fcRvList.layoutManager = GridLayoutManager(mContext, 7)

        return mBinding.root
    }

    inner class CalendarAdapter(): RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ViewCalendarBinding = DataBindingUtil.inflate(inflater, R.layout.view_calendar, parent, false)

            return ViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if(position >= dayWeek - 1){
                Trace.debug("position = $position dayweek = $dayWeek")
                holder.vc_tv_day.text = (position - dayWeek + 2).toString()
            }
        }

        override fun getItemViewType(position: Int): Int = position

        override fun getItemCount(): Int = lastDay + dayWeek - 1

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vc_tv_day = itemView.findViewById<TextView>(R.id.vc_tv_day)

        fun getBinding() = DataBindingUtil.getBinding<ViewCalendarBinding>(itemView) as ViewCalendarBinding

        fun setListener(){
            getBinding().listener = mContext as ComponentItemListener
        }
    }
}