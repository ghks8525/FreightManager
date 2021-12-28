package com.jungbang.transitionmanager.ui.admin

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.DialogScheduleBinding
import com.jungbang.transitionmanager.databinding.ViewAdminUserBinding
import com.jungbang.transitionmanager.databinding.ViewSpinnerItemBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener
import com.jungbang.transitionmanager.ui.common.VerticalItemDecorator

class ScheduleDialog(val arr:ArrayList<String>, val mContext: Context): DialogFragment(), ComponentItemListener {
    lateinit var mBinding: DialogScheduleBinding
    var idx = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DialogScheduleBinding.inflate(inflater, container, false)
        mBinding.listener = this
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window!!.setGravity(Gravity.BOTTOM)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mBinding.dsRvList.adapter = ScheduleAdapter()
        mBinding.dsRvList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mBinding.dsRvList.setHasFixedSize(true)

        return mBinding.root
    }

    override fun onResume() {
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val dm = this.resources.displayMetrics
        val height = dm.heightPixels

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params!!.height = (height * 0.8).toInt()
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams

        super.onResume()
    }

    fun onClick(v: View){
        when(v.id){
            R.id.ds_btn_close -> {
                dismiss()
            }
        }
    }

    override fun onClick(v: View, pos: Int) {
        if(idx != -1){
            val view = mBinding.dsRvList[idx]
            view.findViewById<TextView>(R.id.vsi_tv_item).isSelected = false
            view.findViewById<ImageView>(R.id.vsi_iv_check).visibility = View.GONE
        }

        val vsi_tv_item = v.findViewById<TextView>(R.id.vsi_tv_item)
        val vsi_iv_check = v.findViewById<ImageView>(R.id.vsi_iv_check)

        if(vsi_tv_item.isSelected){
            vsi_tv_item.isSelected = false
            vsi_iv_check.visibility = View.GONE
        }else{
            vsi_tv_item.isSelected = true
            vsi_iv_check.visibility = View.VISIBLE
        }
        idx = pos
    }
    
    inner class ScheduleAdapter: RecyclerView.Adapter<ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ViewSpinnerItemBinding = DataBindingUtil.inflate(inflater, R.layout.view_spinner_item, parent, false)


            return ViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val binding = holder.getBinding()
            binding.pos = position
            binding.listener = this@ScheduleDialog

        }
        override fun getItemCount(): Int = arr.count()
    }

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        fun getBinding() = DataBindingUtil.getBinding<ViewSpinnerItemBinding>(itemView) as ViewSpinnerItemBinding
    }
}