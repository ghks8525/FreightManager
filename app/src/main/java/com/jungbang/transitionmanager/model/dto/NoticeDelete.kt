package com.jungbang.transitionmanager.model.dto

class NoticeDelete {
    data class Response(val success: Boolean) : BaseResponse() {
    }
}
//POST /notice
//설명: 공지사항 등록
//필드: title, content, companyId
//응답: { success: true }