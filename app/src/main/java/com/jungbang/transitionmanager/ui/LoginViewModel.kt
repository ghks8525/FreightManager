package com.jungbang.transitionmanager.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.model.dto.CommonData
import com.jungbang.transitionmanager.model.dto.Login
import com.jungbang.transitionmanager.model.dto.Users
import com.jungbang.transitionmanager.model.http.LoginProtocol
import com.jungbang.transitionmanager.model.http.UsersProtocol
import com.jungbang.transitionmanager.sys.net.HttpResponsable
import com.jungbang.transitionmanager.sys.net.NetworkManager
import com.jungbang.transitionmanager.sys.net.ProtocolFactory
import java.util.*

class LoginViewModel: ViewModel() {
    val mldUser:MutableLiveData<Login.Response.User> = MutableLiveData()

    fun getLogin(account:Login.Request){
        val protocol: LoginProtocol = ProtocolFactory.create(LoginProtocol::class.java)

        protocol.setJsonRequestBody(account)
        protocol.setHttpResponsable(object : HttpResponsable<Login.Response.Data> {
            override fun onResponse(res: Login.Response.Data) {
                Trace.debug("++ Success = ${res.Items[0].companyId}")
                CommonData.userinfo = res.Items[0]
                mldUser.value = CommonData.userinfo
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }
}