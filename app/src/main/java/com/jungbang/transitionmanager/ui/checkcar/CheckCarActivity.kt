package com.jungbang.transitionmanager.ui.checkcar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.jungbang.transitionmanager.databinding.ActivityCheckCarBinding
import com.jungbang.transitionmanager.ui.common.ComponentItemListener
import com.jungbang.transitionmanager.ui.common.VerticalItemDecorator
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.R
import net.daum.mf.map.api.MapView


class CheckCarActivity : AppCompatActivity(), ComponentItemListener {
    lateinit var mBinding: ActivityCheckCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_car)
        mBinding.listener = this

        mBinding.accBtnInOperation.isSelected = true
        try {
            val mapView = MapView(this)
            val mapViewContainer = mBinding.mapView
            mapViewContainer.addView(mapView)
        } catch (e: Exception) {
            Trace.debug("aaaaaaaaaaaaaaaaaaaaa = $e)")
            e.printStackTrace()
        }
//        val bottomSheet = BottomSheetBehavior.from(mBinding.accClBottom)
//        bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED

        val userAdapter = UserAdapter(this)
        mBinding.accRvUserList.adapter = userAdapter
        mBinding.accRvUserList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mBinding.accRvUserList.setHasFixedSize(true)

        val operationAdapter = InOperationAdapter(this)
        mBinding.accRvInOperationList.adapter = operationAdapter
        mBinding.accRvInOperationList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.accRvInOperationList.addItemDecoration(VerticalItemDecorator(1))
        mBinding.accRvInOperationList.setHasFixedSize(true)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.acc_btn_in_operation -> {
                mBinding.accBtnInOperation.isSelected = true
                mBinding.accBtnNotOperation.isSelected = false
            }

            R.id.acc_btn_not_operation -> {
                mBinding.accBtnInOperation.isSelected = false
                mBinding.accBtnNotOperation.isSelected = true
            }

            R.id.acc_btn_user_list -> {
                val bottomSheet = BottomSheetBehavior.from(mBinding.accClBottom)
                if (bottomSheet.state == BottomSheetBehavior.STATE_COLLAPSED)
                    bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                else
                    bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED

            }
        }
    }

    override fun onClick(v: View, pos: Int) {

    }
}