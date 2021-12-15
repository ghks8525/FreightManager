package com.jungbang.transitionmanager.ui.common

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.SingleDialogBinding

class SingleDialog() : DialogFragment(), View.OnClickListener, Parcelable {

    lateinit var mBinding: SingleDialogBinding
    lateinit var title: String
    lateinit var msg: String
    private var singleBtnListener: DialogInterface.OnClickListener? = null
    private var positiveBtnListener: DialogInterface.OnClickListener? = null
    private var negativeBtnListener: DialogInterface.OnClickListener? = null
    private var isSingleBtn: Boolean = true

    constructor(parcel: Parcel) : this() {
    }

    constructor(title:String, msg:String, singleBtnListener: DialogInterface.OnClickListener): this(){
        this.title = title
        this.msg = msg
        this.isSingleBtn = true
        this.singleBtnListener = singleBtnListener
    }

    constructor(title:String, msg:String, positiveBtnListener: DialogInterface.OnClickListener?, negativeBtnListener: DialogInterface.OnClickListener?): this(){
        this.title = title
        this.msg = msg
        this.isSingleBtn = false
        this.positiveBtnListener = positiveBtnListener
        this.negativeBtnListener = negativeBtnListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SingleDialogBinding.inflate(inflater, container, false)

        mBinding.sdTvTitle.text = title
        mBinding.sdTvMsg.text = msg
        if(isSingleBtn){
            mBinding.sdBtnOk.visibility = View.VISIBLE
            mBinding.sdBtnOk.setOnClickListener(this)
        }else{
            mBinding.sdBtnPositive.visibility = View.VISIBLE
            mBinding.sdBtnPositive.setOnClickListener(this)
            mBinding.sdBtnNegative.visibility = View.VISIBLE
            mBinding.sdBtnNegative.setOnClickListener(this)
        }

        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.bg_rect_white_fill_r8)

        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.sd_btn_ok -> {
                singleBtnListener?.onClick(dialog, R.id.sd_btn_ok)
            }

            R.id.sd_btn_positive -> {
                positiveBtnListener?.onClick(dialog, R.id.sd_btn_positive)
            }

            R.id.sd_btn_negative -> {
                negativeBtnListener?.onClick(dialog, R.id.sd_btn_negative)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SingleDialog> {
        override fun createFromParcel(parcel: Parcel): SingleDialog {
            return SingleDialog(parcel)
        }

        override fun newArray(size: Int): Array<SingleDialog?> {
            return arrayOfNulls(size)
        }
    }
}