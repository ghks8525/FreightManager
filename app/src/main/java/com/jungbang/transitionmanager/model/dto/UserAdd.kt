package com.jungbang.transitionmanager.model.dto

import android.os.Parcel
import android.os.Parcelable

class UserAdd {
    data class Response(val boolean: Boolean) : BaseResponse() {

    }

    data class Request(
        val companyId: String,
        val userName: String,
        val phoneNumber: String,
        val recruitType: String
    )
}

////    {
//        "companyId": "1",
//        "userName": "userName",
//        "phoneNumber": "01053967566",
//        "recruitType": "free"
//    }