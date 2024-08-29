package org.gcvd.server.domain.guestbook.application.impl

import org.gcvd.server.domain.guestbook.application.converter.GuestbookConverter
import org.gcvd.server.domain.guestbook.model.repository.GuestbookRepository
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookRequest
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GuestbookService(
    private val guestbookRepository: GuestbookRepository,
) {
    @Transactional(readOnly = true)
    fun getGuestBookList(): GuestbookResponse.GuestbookList {
        val guestbookList = guestbookRepository.findAll()
        return GuestbookConverter.toGuestbookList(guestbookList)
    }

    @Transactional
    fun createGuestBook(request: GuestbookRequest.CreateGuestBook) =
        guestbookRepository.save(GuestbookConverter.toGuestBook(request))
}
