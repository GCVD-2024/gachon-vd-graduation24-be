package org.gcvd.server.domain.work.application.impl

import org.gcvd.server.domain.work.application.converter.WorkConverter
import org.gcvd.server.domain.work.model.repository.WorkRepository
import org.gcvd.server.domain.work.ui.dto.WorkResponse
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class WorkService(
    private val workRepository: WorkRepository,
) {
    fun getWorksList(
        category: String,
        currentPage: Int,
    ): WorkResponse.WorkList {
        if (category.equals("All", ignoreCase = true)) {
            return WorkConverter.toWorkList(
                workRepository.findAll(
                    PageRequest.of(currentPage - 1, WORKS_PER_PAGE, Sort.by("category", "student.studentId")),
                ),
                currentPage,
            )
        }

        val pageable: Pageable = PageRequest.of(currentPage - 1, WORKS_PER_PAGE, Sort.by("student.studentId"))
        return WorkConverter.toWorkList(
            workRepository.findAllByCategory(category, pageable),
            currentPage,
        )
    }

    fun getDetailWork(
        name: String,
        title: String,
    ): WorkResponse.DetailWork =
        WorkConverter.toDetailWork(
            workRepository.findByStudent_StudentNameAndTitle(name, title),
        )

    companion object {
        private const val WORKS_PER_PAGE: Int = 10
    }
}
