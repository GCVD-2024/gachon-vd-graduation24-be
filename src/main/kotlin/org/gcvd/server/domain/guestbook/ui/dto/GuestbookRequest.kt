package org.gcvd.server.domain.guestbook.ui.dto

import io.swagger.v3.oas.annotations.media.Schema

class GuestbookRequest {
    data class CreateGuestBook(
        @Schema(description = "닉네임") val nickname: String,
        @Schema(description = "내용") val content: String,
    )
}
