package org.gcvd.server.domain.work.application.impl

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.gcvd.server.domain.work.application.converter.WorkConverter
import org.gcvd.server.domain.work.model.entity.Student
import org.gcvd.server.domain.work.model.entity.Work
import org.gcvd.server.domain.work.model.repository.WorkRepository
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@SpringBootTest
class WorkServiceTest : AnnotationSpec() {
    protected lateinit var workService: WorkService
    protected lateinit var workRepository: WorkRepository

    @BeforeEach
    fun setUp() {
        workRepository = mockk()
        workService = WorkService(workRepository)
    }

    @Test
    fun getWorksListShouldReturnAllWorksWhenCategoryIsAll() {
        // Given
        val category = "All"
        val currentPage = 5
        val expectedResult = WorkConverter.toWorkList(Page.empty(), currentPage)

        every { workRepository.findAll(any<Pageable>()) } returns Page.empty()

        // When
        val actualResult = workService.getWorksList(category, currentPage)

        // Then
        actualResult shouldBe expectedResult
        verify(exactly = 1) { workRepository.findAll(any<Pageable>()) }
        verify(exactly = 0) {
            workRepository.findAllByCategory(
                any<String>(),
                any<Pageable>(),
            )
        }
    }

    @Test
    fun getWorksListShouldReturnCategory_specificWorksWhenCategoryIsNotAll() {
        // Given
        val category = "UX"
        val currentPage = 5
        val expectedResult = WorkConverter.toWorkList(Page.empty(), currentPage)

        every {
            workRepository.findAllByCategory(
                any<String>(),
                any<Pageable>(),
            )
        } returns Page.empty()

        // When
        val actualResult = workService.getWorksList(category, currentPage)

        // Then
        actualResult shouldBe expectedResult
        verify(exactly = 0) { workRepository.findAll(any<Pageable>()) }
        verify(exactly = 1) {
            workRepository.findAllByCategory(
                any<String>(),
                any<Pageable>(),
            )
        }
    }

    @Test
    fun getDetailWorkShouldReturnCorrectWorkDetailsForGivenStudentNameAndTitle() {
        // Given
        val works =
            Work(
                category = "All",
                title = "title",
                subtitle = "subtitle",
                description = "description",
                detailArt = "detailArt",
                thumbnail = "thumbnail",
                video = "video",
                student =
                    Student(
                        studentId = "studentId",
                        studentName = "studentName",
                        contact = "contact",
                    ),
            )
        val expectedResult = WorkConverter.toDetailWork(works)

        every {
            workRepository.findByStudent_StudentNameAndTitle(
                any<String>(),
                any<String>(),
            )
        } returns works

        // When
        val actualResult = workService.getDetailWork("studentName", "title")

        // Then
        actualResult shouldBe expectedResult
        verify(exactly = 1) {
            workRepository.findByStudent_StudentNameAndTitle(
                any<String>(),
                any<String>(),
            )
        }
    }
}
