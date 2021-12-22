package com.jungbang.transitionmanager.ui.admin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.DialogUserBinding
import com.jungbang.transitionmanager.model.dto.Users

class UserDialog(val mContext: Context, val user:Users.Response.User) : DialogFragment() {
    lateinit var mBinding: DialogUserBinding
    lateinit var mViewModel: UserManageViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DialogUserBinding.inflate(inflater, container, false)
        mBinding.listener = this
        mBinding.data = user
        mViewModel = ViewModelProvider(mContext as UserManageActivity).get(UserManageViewmodel::class.java)

        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.bg_rect_white_fill_r8)
        return mBinding.root
    }

    fun onClick(v: View){
        when(v.id){
            R.id.du_btn_confirm -> {
                dismiss()
            }
            R.id.du_iv_delete -> {
                mViewModel.deleteUser(user.id)
                dismiss()
            }

            R.id.du_iv_edit -> {
                dismiss()
                UserAddDialog(mContext, user).show(parentFragmentManager, null)
            }
        }
    }
}