package org.gcvd.server.domain.guestbook.ui.dto

import io.swagger.v3.oas.annotations.media.Schema

class GuestbookRequest {
    data class CreateGuestBook(
        @Schema(example = "홍길동")
        val nickname: String,
        @Schema(example = "안녕하세요")
        val content: String,
    )
}
