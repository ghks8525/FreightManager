package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.UserDelete
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol
import com.jungbang.transitionmanager.sys.net.HttpConst

class UserDeleteProtocol()  : AbstractHttpProtocol<UserDelete.Response>() {
        var PATH = "user/"

        override fun getUrl() = getDomain() + PATH

        override fun getMethod(): Int = HttpConst.HTTP_DELETE
    }

//    {
//        "companyId": "1",
//        "userName": "userName",
//        "phoneNumber": "01053967566",
//        "recruitType": "free"
//    }
