package com.jungbang.transitionmanager.model.dto

class UserEdit {
    data class Response(val data: Boolean) : BaseResponse() {
    }

    data class Request(
        val companyId: String,
        val userName: String,
        val phoneNumber: String,
        val recruitType: String
    )
}