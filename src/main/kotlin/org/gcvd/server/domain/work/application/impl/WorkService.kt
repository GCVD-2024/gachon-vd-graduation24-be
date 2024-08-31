package org.gcvd.server.domain.work.application.impl

import org.gcvd.server.domain.work.application.converter.WorkConverter
import org.gcvd.server.domain.work.model.repository.WorkRepository
import org.gcvd.server.domain.work.ui.dto.WorkResponse
import org.springframework.stereotype.Service

@Service
class WorkService(
    private val workRepository: WorkRepository,
) {
    fun getWorksList(category: String): WorkResponse.WorkList {
        if (category.equals("All", ignoreCase = true)) {
            val works = workRepository.findAll()
            return WorkConverter.toWorkList(works)
        }

        val works = workRepository.findAllByCategory(category)
        return WorkConverter.toWorkList(works)
    }

    fun getDetailWork(
        name: String,
        title: String,
    ): WorkResponse.DetailWork {
        val detailWork = workRepository.findByTitleAndStudent_StudentName(name, title)
        return WorkConverter.toDetailWork(detailWork)
    }
}
