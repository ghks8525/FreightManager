package com.jungbang.transitionmanager.model.dto

class VehicleEdit {
    data class Response(val data: Boolean) : BaseResponse() {
    }

    data class Request(
        val carModel: String,
        val carSerialNumber: String
    )
}