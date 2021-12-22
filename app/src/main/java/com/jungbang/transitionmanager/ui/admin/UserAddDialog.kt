package com.jungbang.transitionmanager.ui.admin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.DialogUserAddBinding
import com.jungbang.transitionmanager.model.dto.UserAdd
import com.jungbang.transitionmanager.model.dto.Users

class UserAddDialog() : DialogFragment() {
    lateinit var mContext: Context
    lateinit var mViewModel: UserManageViewmodel
    var data: Users.Response.User? = null

    constructor(context: Context) : this() {
        this.mContext = context
    }

    constructor(context: Context, data: Users.Response.User) : this() {
        this.mContext = context
        this.data = data
    }

    lateinit var mBinding: DialogUserAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DialogUserAddBinding.inflate(inflater, container, false)
        mBinding.listener = this
        mBinding.duaTvTitle.text = getString(R.string.edit_user)

        mViewModel =
            ViewModelProvider(mContext as UserManageActivity).get(UserManageViewmodel::class.java)

        if (data != null) {
            mBinding.duaEtUserName.setText(data!!.userName)
            mBinding.duaEtUserPhone.setText(data!!.phoneNumber)
            mBinding.duaRbFree.isChecked = data!!.recruitType == "free"
        }

        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.bg_rect_white_fill_r8)
        return mBinding.root
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.dua_btn_cancel -> {
                dismiss()
            }
            R.id.dua_btn_confirm -> {
                if (data == null) {
                    mViewModel.addUser(
                        UserAdd.Request("1",
                            mBinding.duaEtUserName.text.toString(),
                            mBinding.duaEtUserPhone.text.toString(),
                            let { if (mBinding.duaRbFree.isChecked) "free" else "regular" })
                    )
                } else {
                    mViewModel.editUser(
                        Users.Response.User(
                            mBinding.duaEtUserPhone.text.toString(),
                            data!!.createDate,
                            data!!.companyId,
                            let { if(mBinding.duaRbFree.isChecked) "free" else "regular" },
                            mBinding.duaEtUserName.text.toString(),
                            data!!.id
                        )
                    )
                }
                dismiss()
            }
        }
    }
}