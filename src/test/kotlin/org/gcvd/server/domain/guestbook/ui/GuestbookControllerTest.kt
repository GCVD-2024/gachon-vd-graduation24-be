package org.gcvd.server.domain.guestbook.ui

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.gcvd.server.common.response.ResponseDto
import org.gcvd.server.domain.guestbook.application.converter.GuestbookConverter
import org.gcvd.server.domain.guestbook.application.impl.GuestbookService
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookRequest
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookResponse
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest
class GuestbookControllerTest : AnnotationSpec() {
    private lateinit var guestbookController: GuestbookController
    private lateinit var guestbookService: GuestbookService

    @BeforeEach
    fun setUp() {
        guestbookService = mockk()
        guestbookController = GuestbookController(guestbookService)
    }

    @Test
    fun getGuestBookListShouldReturnResponseDtoWithAllSavedGuestbooks() {
        // Given
        val guestbookList =
            GuestbookResponse.GuestbookList(
                emptyList(),
            )
        val expectedResponse = ResponseDto.onSuccess(guestbookList)

        every { guestbookService.getGuestBookList() } returns guestbookList

        // When
        val actualResponse = guestbookController.getGuestBookList()

        // Then
        actualResponse shouldBe expectedResponse
        verify(exactly = 1) { guestbookService.getGuestBookList() }
    }

    @Test
    fun createGuestBookShouldSaveRequestInDBAndReturnResponseDtoWithNoContent() {
        // Given
        val request =
            GuestbookRequest.CreateGuestBook(
                nickname = "홍길동",
                content = "안녕하세요",
            )

        every { guestbookService.createGuestBook(request) } returns GuestbookConverter.toGuestBook(request)

        // When
        val actualResponse = guestbookController.createGuestBook(request)

        // Then
        actualResponse should beInstanceOf<ResponseDto<Nothing?>>()
        verify(exactly = 1) { guestbookService.createGuestBook(request) }
    }
}
