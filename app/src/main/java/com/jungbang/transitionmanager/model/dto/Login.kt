package com.jungbang.transitionmanager.model.dto

class Login {
    data class Response(val data: Data) : BaseResponse() {
        data class Data(
            val Items: ArrayList<User>,
            val Count: Int,
            val ScannedCount: Int
        )

        data class User(
            val phoneNumber: String,
            val userPassword: String,
            val createDate: String,
            val companyId: String,
            val recruitType: String,
            val userName: String,
            val id: Int
        )
    }

    data class Request(
        val phoneNumber: String,
        val userPassword: String
    )

}