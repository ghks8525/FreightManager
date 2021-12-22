package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.Login
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol
import com.jungbang.transitionmanager.sys.net.HttpConst

class LoginProtocol()  : AbstractHttpProtocol<Login.Response.Data>() {
        var PATH = "userLogin"

        override fun getUrl() = getDomain() + PATH

        override fun getMethod(): Int = HttpConst.HTTP_POST
    }

//    {
//        "companyId": "1",
//        "userName": "userName",
//        "phoneNumber": "01053967566",
//        "recruitType": "free"
//    }
