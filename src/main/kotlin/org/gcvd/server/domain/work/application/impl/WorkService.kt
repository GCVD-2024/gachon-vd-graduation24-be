package org.gcvd.server.domain.work.application.impl

import org.gcvd.server.domain.work.application.converter.WorkConverter
import org.gcvd.server.domain.work.model.repository.WorkRepository
import org.gcvd.server.domain.work.ui.dto.WorkResponse
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class WorkService(
    private val workRepository: WorkRepository,
) {
    @Cacheable(value = ["workList"], key = "#category + '_' + #currentPage")
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

    @Cacheable(value = ["workDetails"], key = "#name + '_' + #title")
    fun getDetailWork(
        name: String,
        title: String,
    ): WorkResponse.DetailWork =
        WorkConverter.toDetailWork(
            workRepository.findByStudent_StudentNameAndTitle(name, title),
        )

    @CacheEvict(value = ["workList", "workDetails"], allEntries = true)
    fun clearCache(): Boolean = true

    companion object {
        private const val WORKS_PER_PAGE: Int = 10
    }
}
