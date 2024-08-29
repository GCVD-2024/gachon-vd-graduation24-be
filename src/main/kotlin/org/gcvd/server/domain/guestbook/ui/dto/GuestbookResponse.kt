package org.gcvd.server.domain.guestbook.ui.dto

class GuestbookResponse {
    data class GuestbookList(
        val guestbooks: List<GuestbookDetail>,
    )

    data class GuestbookDetail(
        val nickname: String,
        val content: String,
        val createdAt: String,
    )
}
