package org.gcvd.server.domain.guestbook.application.impl

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.gcvd.server.domain.guestbook.application.converter.GuestbookConverter
import org.gcvd.server.domain.guestbook.model.entity.Guestbook
import org.gcvd.server.domain.guestbook.model.repository.GuestbookRepository
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookRequest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GuestbookServiceTest : AnnotationSpec() {
    private lateinit var guestbookService: GuestbookService
    private lateinit var guestbookRepository: GuestbookRepository

    @BeforeEach
    fun setUp() {
        guestbookRepository = mockk()
        guestbookService = GuestbookService(guestbookRepository)
    }

    @Test
    fun getGuestBookListShouldReturnConvertedListOfAllGuestbooks() {
        // Given
        val guestbooks =
            listOf(
                Guestbook(
                    nickname = "홍길동",
                    content = "안녕하세요",
                ),
            )
        val expectedResult = GuestbookConverter.toGuestbookList(guestbooks)

        every { guestbookRepository.findAll() } returns guestbooks

        // When
        val actualResult = guestbookService.getGuestBookList()

        // Then
        actualResult shouldBe expectedResult
        verify(exactly = 1) { guestbookRepository.findAll() }
    }

    @Test
    fun createGuestBookShouldSaveAndReturnCreatedGuestbook() {
        // Given
        val request =
            Guestbook(
                nickname = "홍길동",
                content = "안녕하세요",
            )
        every { guestbookRepository.save(any()) } returns request

        // When
        val actualResult =
            guestbookService.createGuestBook(
                GuestbookRequest.CreateGuestBook(
                    request.nickname,
                    request.content,
                ),
            )

        // Then
        actualResult.nickname shouldBe request.nickname
        actualResult.content shouldBe request.content
        verify(exactly = 1) {
            guestbookRepository.save(
                match { it.nickname == request.nickname && it.content == request.content },
            )
        }
    }
}
