package com.jungbang.transitionmanager.model.http

import com.jungbang.transitionmanager.model.dto.Users
import com.jungbang.transitionmanager.model.dto.Vehicles
import com.jungbang.transitionmanager.sys.net.AbstractHttpProtocol

class VehiclesProtocol  : AbstractHttpProtocol<Vehicles.Response.Data>() {
        val PATH = "vehicle/"

        override fun getUrl() = getDomain() + PATH
    }

//    {
//        "companyId": "1",
//        "userName": "userName",
//        "phoneNumber": "01053967566",
//        "recruitType": "free"
//    }
