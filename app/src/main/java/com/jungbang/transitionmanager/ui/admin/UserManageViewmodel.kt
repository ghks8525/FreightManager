package com.jungbang.transitionmanager.ui.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.model.dto.UserAdd
import com.jungbang.transitionmanager.model.dto.UserDelete
import com.jungbang.transitionmanager.model.dto.UserEdit
import com.jungbang.transitionmanager.model.dto.Users
import com.jungbang.transitionmanager.model.http.UserAddProtocol
import com.jungbang.transitionmanager.model.http.UserDeleteProtocol
import com.jungbang.transitionmanager.model.http.UserEditProtocol
import com.jungbang.transitionmanager.model.http.UsersProtocol
import com.jungbang.transitionmanager.sys.net.HttpResponsable
import com.jungbang.transitionmanager.sys.net.NetworkManager
import com.jungbang.transitionmanager.sys.net.ProtocolFactory

class UserManageViewmodel : ViewModel() {
    val mldUsers: MutableLiveData<Users.Response.Data> = MutableLiveData()

    fun getUsers() {
        val protocol: UsersProtocol = ProtocolFactory.create(UsersProtocol::class.java)

        protocol.setHttpResponsable(object : HttpResponsable<Users.Response.Data> {
            override fun onResponse(res: Users.Response.Data) {
                Trace.debug("++ Success = $res")
                mldUsers.value = res
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun getUser() {
        val protocol: UsersProtocol = ProtocolFactory.create(UsersProtocol::class.java)

        protocol.setHttpResponsable(object : HttpResponsable<Users.Response.Data> {
            override fun onResponse(res: Users.Response.Data) {
                Trace.debug("++ Success = $res")
                mldUsers.value = res
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun addUser(data: UserAdd.Request) {
        val protocol: UserAddProtocol = ProtocolFactory.create(UserAddProtocol::class.java)
        val request = data
        protocol.setJsonRequestBody(request)
        protocol.setHttpResponsable(object : HttpResponsable<UserAdd.Response> {
            override fun onResponse(res: UserAdd.Response) {
                Trace.debug("++ Success")
                getUsers()
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun deleteUser(id: Int) {
        val protocol: UserDeleteProtocol = ProtocolFactory.create(UserDeleteProtocol::class.java)

        protocol.PATH += id
        protocol.setHttpResponsable(object : HttpResponsable<UserDelete.Response> {
            override fun onResponse(res: UserDelete.Response) {
                Trace.debug("++ Success")
                getUsers()
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun editUser(user: Users.Response.User) {
        val protocol: UserEditProtocol = ProtocolFactory.create(UserEditProtocol::class.java)

        protocol.PATH += user.id
        protocol.setJsonRequestBody(
            UserEdit.Request(
                user.companyId,
                user.userName,
                user.phoneNumber,
                user.recruitType
            )
        )
        protocol.setHttpResponsable(object : HttpResponsable<UserEdit.Response> {
            override fun onResponse(res: UserEdit.Response) {
                Trace.debug("++ Success")
                getUsers()
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }
}