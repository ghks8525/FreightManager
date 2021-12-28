package com.jungbang.transitionmanager.model.dto

class NoticeEdit {
    data class Response(val success: Boolean) : BaseResponse() {
    }

    data class Request(
        val title: String,
        val content: String
    )

}
//POST /notice
//설명: 공지사항 등록
//필드: title, content
//응답: { success: true }
