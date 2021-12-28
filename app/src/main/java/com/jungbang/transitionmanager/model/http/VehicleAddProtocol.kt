package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.UserAdd
import com.jungbang.transitionmanager.model.dto.VehicleAdd
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol
import com.jungbang.transitionmanager.sys.net.HttpConst

class VehicleAddProtocol  : AbstractHttpProtocol<VehicleAdd.Response>() {
        val PATH = "vehicle"

        override fun getUrl() = getDomain() + PATH

        override fun getMethod(): Int = HttpConst.HTTP_POST
    }

//    {
//        "companyId": "1",
//        "userName": "userName",
//        "phoneNumber": "01053967566",
//        "recruitType": "free"
//    }
