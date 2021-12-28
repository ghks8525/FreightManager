package com.jungbang.transitionmanager.model.dto

class Vehicles {
    data class Response(val data: Data) : BaseResponse() {
        data class Data(
            val items: ArrayList<Vehicle>,
            val Count: Int,
            val ScannedCount: Int
        )

        data class Vehicle(
            val carLocation: String,
            val createDate: String,
            val companyId: String,
            val carSerialNumber: String,
            val carModel: String,
            val id: Int,
            val carStatus: String
        )
    }
}