package com.jungbang.transitionmanager.ui.admin

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityAdminScheduleBinding

class AdminScheduleActivity:AppCompatActivity(){
    lateinit var mBinding:ActivityAdminScheduleBinding

    val arr = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_admin_schedule)
        mBinding.listener = this

        arr.add("a")
        arr.add("a")
        arr.add("a")
        arr.add("a")
        arr.add("a")
        arr.add("a")
        arr.add("a")
        arr.add("a")
        arr.add("a")
        arr.add("a")
        arr.add("a")


    }

    fun onClick(v: View){
        when(v.id){
            R.id.aas_sp_car -> {
                ScheduleDialog(arr,this).show(supportFragmentManager,null)
            }
            R.id.aas_sp_date -> {}
            R.id.aas_sp_user -> {

                ScheduleDialog(arr, this).show(supportFragmentManager,null)
            }
        }
    }

}