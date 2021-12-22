package com.jungbang.transitionmanager.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.databinding.ActivityLoginBinding
import com.jungbang.transitionmanager.model.dto.Login
import com.jungbang.transitionmanager.sys.utils.Utils
import com.jungbang.transitionmanager.ui.admin.AdminMainActivity
import com.jungbang.transitionmanager.ui.admin.UserManageViewmodel
import com.jungbang.transitionmanager.ui.user.UserMainActivity
import okhttp3.internal.wait

class LoginActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityLoginBinding
    val mViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.listener = this

        mViewModel.mldUser.observe(this, this::onDataListChanged)
    }

    fun onDataListChanged(data: Login.Response.User) {
        Utils.setPrefString(this, "id", mBinding.alCeId.getText())
        Utils.setPrefString(this, "pw", mBinding.alCePw.getText())
        Utils.setPrefString(this, "companyId", data.companyId)
        Utils.setPrefInt(this, "userId", data.id)

        val intent = if (data.recruitType == "admin") Intent(
            this,
            AdminMainActivity::class.java
        ) else
            Intent(this, UserMainActivity::class.java)


        startActivity(intent)

    }


    fun onClick(v: View) {
        when (v.id) {
            R.id.al_btn_login -> {
                mViewModel.getLogin(
                    Login.Request(
                        mBinding.alCeId.getText(),
                        mBinding.alCePw.getText()
                    )
                )
            }
        }
    }

}