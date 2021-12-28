package com.jungbang.transitionmanager.ui.notice

import android.util.SparseBooleanArray
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.model.dto.*
import com.jungbang.transitionmanager.model.http.*
import com.jungbang.transitionmanager.sys.net.HttpResponsable
import com.jungbang.transitionmanager.sys.net.NetworkManager
import com.jungbang.transitionmanager.sys.net.ProtocolFactory

class NoticeViewModel: ViewModel() {
    val mldNotices:MutableLiveData<Notices.Response.Data> = MutableLiveData()


    fun getNotice(){
        val protocol: NoticesProtocol = ProtocolFactory.create(NoticesProtocol::class.java)
        if(CommonData.userinfo == null)
            return

        protocol.PATH = protocol.PATH + CommonData.userinfo!!.companyId
        protocol.setHttpResponsable(object : HttpResponsable<Notices.Response.Data> {
            override fun onResponse(res: Notices.Response.Data) {
                Trace.debug("++ Success = ${mldNotices.value}")
                mldNotices.value = res
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun addNotice(data: NoticeAdd.Request){
        val protocol: NoticeAddProtocol = ProtocolFactory.create(NoticeAddProtocol::class.java)

        protocol.setJsonRequestBody(data)
        protocol.setHttpResponsable(object : HttpResponsable<NoticeAdd.Response> {
            override fun onResponse(res: NoticeAdd.Response) {
                getNotice()
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun editNotice(id:Int, data: NoticeEdit.Request){
        val protocol: NoticeEditProtocol = ProtocolFactory.create(NoticeEditProtocol::class.java)
        if(CommonData.userinfo == null)
            return

        protocol.PATH = protocol.PATH + id
        protocol.setJsonRequestBody(data)
        protocol.setHttpResponsable(object : HttpResponsable<NoticeEdit.Response> {
            override fun onResponse(res: NoticeEdit.Response) {
                getNotice()
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun deleteNotice(id:Int){
        val protocol: NoticeDeleteProtocol = ProtocolFactory.create(NoticeDeleteProtocol::class.java)

        protocol.PATH = protocol.PATH + id
        protocol.setHttpResponsable(object : HttpResponsable<NoticeDelete.Response> {
            override fun onResponse(res: NoticeDelete.Response) {
                getNotice()
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
                Trace.debug("++ Fail = $nError, $strMsg")
            }
        })
        NetworkManager.getInstance().asyncRequest(protocol)
    }
}