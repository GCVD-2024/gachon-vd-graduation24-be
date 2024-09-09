package org.gcvd.server.domain.viewcount.ui

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.gcvd.server.common.response.ResponseDto
import org.gcvd.server.domain.viewcount.application.impl.ViewCountService
import org.gcvd.server.domain.viewcount.ui.dto.ViewCountResponse
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest
class ViewCountControllerTest : AnnotationSpec() {
    private lateinit var viewCountController: ViewCountController
    private lateinit var viewCountService: ViewCountService

    @BeforeEach
    fun setUp() {
        viewCountService = mockk()
        viewCountController = ViewCountController(viewCountService)
    }

    @Test
    fun getViewCountTest() {
        // Given
        val expectedCount = 5
        val expectedResponse = ResponseDto.onSuccess(ViewCountResponse.ViewCount(expectedCount))

        every { viewCountService.getViewCount() } returns ViewCountResponse.ViewCount(expectedCount)

        // When
        val actualResponse = viewCountController.getViewCount()

        // Then
        actualResponse shouldBe expectedResponse
        verify(exactly = 1) { viewCountService.getViewCount() }
    }
}
