package org.gcvd.server.domain.guestbook.application.impl

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.should
import io.mockk.every
import io.mockk.mockk
import org.gcvd.server.domain.guestbook.application.converter.GuestbookConverter
import org.gcvd.server.domain.guestbook.model.entity.Guestbook
import org.gcvd.server.domain.guestbook.model.repository.GuestbookRepository
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import kotlin.test.Test

@SpringBootTest
class GuestbookServiceTest : AnnotationSpec() {
    @Autowired private lateinit var guestbookService: GuestbookService
    private lateinit var guestbookRepository: GuestbookRepository

    @BeforeEach
    fun setUp() {
        guestbookRepository = mockk()
        guestbookService = GuestbookService(guestbookRepository)
    }

    @Test
    fun getGuestBookListTest() {
        // Given
        val guestbooks =
            listOf(
                Guestbook(
                    nickname = "홍길동",
                    content = "안녕하세요",
                    createdAt = LocalDateTime.now(),
                ),
            )
        every { guestbookRepository.findAll() } returns guestbooks

        // When
        val expectedGuestbooks = guestbookService.getGuestBookList()

        // Then
        expectedGuestbooks.shouldBeEqual(GuestbookConverter.toGuestbookList(guestbooks))
    }

    @Test
    fun createGuestBookTest() {
        // Given
        val request =
            Guestbook(
                nickname = "홍길동",
                content = "안녕하세요",
                createdAt = LocalDateTime.now(),
            )
        every { guestbookRepository.save(any()) } returns request

        // When
        val guestbook =
            guestbookService.createGuestBook(
                GuestbookRequest.CreateGuestBook(
                    request.nickname,
                    request.content,
                ),
            )

        // Then
        guestbook.should {
            it.nickname.shouldBeEqual(request.nickname)
            it.content.shouldBeEqual(request.content)
        }
    }
}
