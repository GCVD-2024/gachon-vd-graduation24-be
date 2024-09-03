package org.gcvd.server.domain.work.application.impl

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.every
import io.mockk.mockk
import org.gcvd.server.domain.work.application.converter.WorkConverter
import org.gcvd.server.domain.work.model.entity.Student
import org.gcvd.server.domain.work.model.entity.Work
import org.gcvd.server.domain.work.model.repository.WorkRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import kotlin.test.Test

@SpringBootTest
class WorkServiceTest : AnnotationSpec() {
    @Autowired private lateinit var workService: WorkService
    private lateinit var workRepository: WorkRepository

    @BeforeEach
    fun setUp() {
        workRepository = mockk()
        workService = WorkService(workRepository)
    }

    @Test
    fun getWorksListTest() {
        // Given
        val category = "All"
        val currentPage = 5

        every { workRepository.findAll(any<Pageable>()) } returns Page.empty()
        every { workRepository.findAllByCategory(any<String>(), any<Pageable>()) } returns Page.empty()

        // When
        val worksList = workService.getWorksList(category, currentPage)

        // Then
        worksList.shouldBeEqual(WorkConverter.toWorkList(Page.empty(), currentPage))
    }

    @Test
    fun getDetailWorkTest() {
        // Given
        val works =
            Work(
                category = "All",
                title = "title",
                subtitle = "subtitle",
                description = "description",
                detailArt = "detailArt",
                thumbnail = "thumbnail",
                student =
                    Student(
                        studentId = "studentId",
                        studentName = "studentName",
                        contact = "contact",
                    ),
            )

        every { workRepository.findByStudent_StudentNameAndTitle(any<String>(), any<String>()) } returns works

        // When
        val detailWork = workService.getDetailWork("studentName", "title")

        // Then
        detailWork.shouldBeEqual(WorkConverter.toDetailWork(works))
    }
}
