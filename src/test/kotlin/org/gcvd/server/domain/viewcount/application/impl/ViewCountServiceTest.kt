package org.gcvd.server.domain.viewcount.application.impl

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.gcvd.server.domain.viewcount.model.entity.ViewCount
import org.gcvd.server.domain.viewcount.model.repository.ViewCountRepository
import org.gcvd.server.domain.viewcount.ui.dto.ViewCountResponse
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class ViewCountServiceTest : AnnotationSpec() {
    private lateinit var viewCountService: ViewCountService
    private lateinit var viewCountRepository: ViewCountRepository
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @BeforeEach
    fun setUp() {
        viewCountRepository = mockk()
        redisTemplate = mockk()
        viewCountService = ViewCountService(redisTemplate, viewCountRepository)
    }

    @Test
    fun getViewCountTest() {
        // Given
        val expectedCount = 5

        every {
            viewCountRepository.getReferenceByName(any())
        } returns
            ViewCount(
                name = ViewCount.VIEW_COUNT_KEY,
                count = 0,
            )
        every {
            redisTemplate
                .opsForValue()
                .get(any())
        } returns expectedCount.toString()

        // When
        val actualResult = viewCountService.getViewCount()

        // Then
        actualResult shouldBe ViewCountResponse.ViewCount(expectedCount)
        verify(exactly = 1) {
            redisTemplate
                .opsForValue()
                .get(any())
        }
        verify(exactly = 1) { viewCountRepository.getReferenceByName(any()) }
    }
}
