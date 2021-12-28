package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.NoticeAdd
import com.jungbang.transitionmanager.model.dto.NoticeDelete
import com.jungbang.transitionmanager.model.dto.Notices
import com.jungbang.transitionmanager.model.dto.Users
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol
import com.jungbang.transitionmanager.sys.net.HttpConst

class NoticeDeleteProtocol : AbstractHttpProtocol<NoticeDelete.Response>() {
    var PATH = "notice/"

    override fun getUrl() = getDomain() + PATH

    override fun getMethod(): Int  = HttpConst.HTTP_DELETE
}

//POST /notice
//설명: 공지사항 등록
//필드: title, content, companyId
//응답: { success: true }