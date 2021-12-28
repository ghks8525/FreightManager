package com.jungbang.transitionmanager.model.dto

class Notices {
    data class Response(val data: Data) : BaseResponse() {
        data class Data(
            val Items: ArrayList<Notice>,
            val Count: Int,
            val ScannedCount: Int
        )

        data class Notice(
            val content: String,
            val createDate: String,
            val companyId: String,
            val id: Int,
            val title: String,
            var selected: Boolean = false
        )
    }

}
