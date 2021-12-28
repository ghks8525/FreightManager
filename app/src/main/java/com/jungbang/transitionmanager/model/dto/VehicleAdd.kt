package com.jungbang.transitionmanager.model.dto

import android.os.Parcel
import android.os.Parcelable

class VehicleAdd {
    data class Response(val boolean: Boolean) : BaseResponse() {

    }

    data class Request(
        val companyId: String,
        val carSerialNumber: String,
        val carModel: String
    )
}

////    {
//        "companyId": "1",
//        "userName": "userName",
//        "phoneNumber": "01053967566",
//        "recruitType": "free"
//    }