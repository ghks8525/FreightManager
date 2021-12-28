package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.Notices
import com.jungbang.transitionmanager.model.dto.Users
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol

class NoticesProtocol  : AbstractHttpProtocol<Notices.Response.Data>() {
        var PATH = "notice/"

        override fun getUrl() = getDomain() + PATH
    }

//    {
//        "companyId": "1",
//        "userName": "userName",
//        "phoneNumber": "01053967566",
//        "recruitType": "free"
//    }
