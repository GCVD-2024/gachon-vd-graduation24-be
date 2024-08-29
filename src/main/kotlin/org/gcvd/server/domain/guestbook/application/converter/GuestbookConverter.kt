package org.gcvd.server.domain.guestbook.application.converter

import org.gcvd.server.domain.guestbook.model.entity.Guestbook
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookRequest
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookResponse

class GuestbookConverter {
    companion object {
        fun toGuestbookList(guestbooks: List<Guestbook>): GuestbookResponse.GuestbookList {
            val guestbookList =
                guestbooks.map {
                    GuestbookResponse.GuestbookDetail(
                        nickname = it.nickname,
                        content = it.content,
                        createdAt = it.createdAt.toString(),
                    )
                }
            return GuestbookResponse.GuestbookList(guestbookList)
        }

        fun toGuestBook(request: GuestbookRequest.CreateGuestBook): Guestbook =
            Guestbook(
                nickname = request.nickname,
                content = request.content,
            )
    }
}
