package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.UserAdd
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol
import com.jungbang.transitionmanager.sys.net.HttpConst

class UserAddProtocol  : AbstractHttpProtocol<UserAdd.Response>() {
        val PATH = "user"

        override fun getUrl() = getDomain() + PATH

        override fun getMethod(): Int = HttpConst.HTTP_POST
    }

//    {
//        "companyId": "1",
//        "userName": "userName",
//        "phoneNumber": "01053967566",
//        "recruitType": "free"
//    }
