package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.UserDelete
import com.jungbang.transitionmanager.model.dto.UserEdit
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol
import com.jungbang.transitionmanager.sys.net.HttpConst

class UserEditProtocol()  : AbstractHttpProtocol<UserEdit.Response>() {
        var PATH = "user/"

        override fun getUrl() = getDomain() + PATH

        override fun getMethod(): Int = HttpConst.HTTP_PUT
    }

