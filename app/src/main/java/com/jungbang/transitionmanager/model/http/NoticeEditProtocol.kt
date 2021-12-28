package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.NoticeAdd
import com.jungbang.transitionmanager.model.dto.NoticeEdit
import com.jungbang.transitionmanager.model.dto.Notices
import com.jungbang.transitionmanager.model.dto.Users
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol
import com.jungbang.transitionmanager.sys.net.HttpConst

class NoticeEditProtocol : AbstractHttpProtocol<NoticeEdit.Response>() {
    var PATH = "notice/"

    override fun getUrl() = getDomain() + PATH

    override fun getMethod(): Int  = HttpConst.HTTP_PUT
}

//POST /notice
//설명: 공지사항 등록
//필드: title, content, companyId
//응답: { success: true }
