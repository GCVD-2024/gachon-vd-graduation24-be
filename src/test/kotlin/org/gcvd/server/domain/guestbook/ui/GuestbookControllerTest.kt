package org.gcvd.server.domain.guestbook.ui

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import org.gcvd.server.common.response.ResponseDto
import org.gcvd.server.domain.guestbook.application.impl.GuestbookService
import org.gcvd.server.domain.guestbook.model.entity.Guestbook
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookRequest
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import java.time.LocalDateTime
import kotlin.test.Test

@WebMvcTest
class GuestbookControllerTest : AnnotationSpec() {
    @Autowired private lateinit var guestbookController: GuestbookController
    private lateinit var guestbookService: GuestbookService

    @BeforeEach
    fun setUp() {
        guestbookService = mockk()
        guestbookController = GuestbookController(guestbookService)
    }

    @Test
    fun getGuestBookList() {
        // Given
        every { guestbookService.getGuestBookList() } returns
            GuestbookResponse.GuestbookList(
                emptyList(),
            )

        // When
        val expectedResponse = guestbookController.getGuestBookList()

        // Then
        expectedResponse.shouldBeTypeOf<ResponseDto<GuestbookResponse.GuestbookList>>()
    }

    @Test
    fun createGuestBook() {
        // Given
        val request =
            GuestbookRequest.CreateGuestBook(
                nickname = "홍길동",
                content = "안녕하세요",
            )
        every { guestbookService.createGuestBook(request) } returns
            Guestbook(
                nickname = "홍길동",
                content = "안녕하세요",
                createdAt = LocalDateTime.now(),
            )

        // When
        val expectedResponse = guestbookController.createGuestBook(request)

        // Then
        expectedResponse.shouldBeTypeOf<ResponseDto<Nothing?>>()
    }
}
