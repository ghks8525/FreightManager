package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.UserDelete
import com.jungbang.transitionmanager.model.dto.UserEdit
import com.jungbang.transitionmanager.model.dto.VehicleEdit
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol
import com.jungbang.transitionmanager.sys.net.HttpConst

class VehicleEditProtocol()  : AbstractHttpProtocol<VehicleEdit.Response>() {
        var PATH = "vehicle/"

        override fun getUrl() = getDomain() + PATH

        override fun getMethod(): Int = HttpConst.HTTP_PUT
    }

