package org.gcvd.server.domain.work.ui

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.gcvd.server.common.response.ResponseDto
import org.gcvd.server.domain.work.application.impl.WorkService
import org.gcvd.server.domain.work.ui.dto.WorkResponse
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest
class WorkControllerTest : AnnotationSpec() {
    private lateinit var workController: WorkController
    private lateinit var workService: WorkService

    @BeforeEach
    fun setUp() {
        workService = mockk()
        workController = WorkController(workService)
    }

    @Test
    fun getWorksListShouldReturnResponseDtoWithWorks() {
        // Given
        val category = "All"
        val currentPage = 5
        val workList =
            WorkResponse.WorkList(
                currentPage = 1,
                totalPage = 1,
                totalWorks = 1,
                works = emptyList(),
            )
        val expectedResponse = ResponseDto.onSuccess(workList)

        every { workService.getWorksList(any<String>(), any<Int>()) } returns workList

        // When
        val actualResponse = workController.getWorksList(category, currentPage)

        // Then
        actualResponse shouldBe expectedResponse
        verify(exactly = 1) { workService.getWorksList(category, currentPage) }
    }

    @Test
    fun getDetailWorkShouldReturnResponseDtoWithSpecificWorkInformationUsingStudentNameAndTitle() {
        // Given
        val detailWork =
            WorkResponse.DetailWork(
                studentName = "홍길동",
                studentId = "201900000",
                contact = "010-1234-5678",
                category = "졸업작품",
                title = "졸업작품 제목",
                subtitle = "졸업작품 부제",
                description = "졸업작품 설명",
                detailArtUrl = "detail_url_1",
                thumbnailUrl = "thumbnail_url_1",
            )
        val expectedResponse = ResponseDto.onSuccess(detailWork)

        every { workService.getDetailWork(any<String>(), any<String>()) } returns detailWork

        // When
        val actualResponse = workController.getDetailWork("홍길동", "졸업작품 제목")

        // Then
        actualResponse shouldBe expectedResponse
        verify(exactly = 1) { workService.getDetailWork("홍길동", "졸업작품 제목") }
    }
}
