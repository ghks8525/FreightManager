package com.jungbang.transitionmanager.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.DialogHistoryBinding
import com.jungbang.transitionmanager.databinding.SingleDialogBinding

class AdminHistoryDialog: DialogFragment() {
    lateinit var mBinding:DialogHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DialogHistoryBinding.inflate(inflater, container, false)
        mBinding.listener = this
        
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.bg_rect_white_fill_r8)
        return mBinding.root
    }

    fun onClick(v:View){
        when(v.id){
            R.id.dh_btn_confirm -> {
                dismiss()
            }
        }
    }
}