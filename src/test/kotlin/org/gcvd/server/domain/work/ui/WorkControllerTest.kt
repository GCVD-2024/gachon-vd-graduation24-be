package org.gcvd.server.domain.work.ui

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import org.gcvd.server.common.response.ResponseDto
import org.gcvd.server.domain.work.application.impl.WorkService
import org.gcvd.server.domain.work.ui.dto.WorkResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import kotlin.test.Test

@WebMvcTest
class WorkControllerTest : AnnotationSpec() {
    @Autowired private lateinit var workController: WorkController
    private lateinit var workService: WorkService

    @BeforeEach
    fun setUp() {
        workService = mockk()
        workController = WorkController(workService)
    }

    @Test
    fun getWorksListTest() {
        // Given
        val category = "All"
        val currentPage = 5

        every { workService.getWorksList(any<String>(), any<Int>()) } returns
            WorkResponse.WorkList(
                currentPage = 1,
                totalPage = 1,
                totalWorks = 1,
                works = emptyList(),
            )

        // When
        val expectedResponse = workController.getWorksList(category, currentPage)

        // Then
        expectedResponse.shouldBeTypeOf<ResponseDto<WorkResponse.WorkList>>()
    }

    @Test
    fun getDetailWorkTest() {
        // Given
        every { workService.getDetailWork(any<String>(), any<String>()) } returns
            WorkResponse.DetailWork(
                studentName = "",
                studentId = "",
                contact = "",
                category = "",
                title = "",
                subtitle = "",
                description = "",
                detailArtUrl = "",
                thumbnailUrl = "",
            )

        // When
        val expectedResponse = workController.getDetailWork("홍길동", "졸업작품")

        // Then
        expectedResponse.shouldBeTypeOf<ResponseDto<WorkResponse.DetailWork>>()
    }
}
