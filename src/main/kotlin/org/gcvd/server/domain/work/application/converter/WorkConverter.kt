package org.gcvd.server.domain.work.application.converter

import org.gcvd.server.domain.work.model.entity.Work
import org.gcvd.server.domain.work.ui.dto.WorkResponse
import org.springframework.data.domain.Page

class WorkConverter {
    companion object {
        fun toWorkList(
            works: Page<Work>,
            currentPage: Int,
        ): WorkResponse.WorkList {
            if (works.isEmpty) {
                return WorkResponse.WorkList(0, 0, 0, emptyList())
            }

            val workList =
                works.toList().map {
                    WorkResponse.WorkInfo(
                        category = it.category,
                        title = it.title,
                        studentName = it.student.studentName,
                        thumbnailUrl = it.thumbnail,
                    )
                }
            return WorkResponse.WorkList(currentPage, works.totalPages, works.totalElements.toInt(), workList)
        }

        fun toDetailWork(work: Work?): WorkResponse.DetailWork {
            if (work == null) {
                return WorkResponse.emptyDetailWork()
            }

            return WorkResponse.DetailWork(
                studentName = work.student.studentName,
                studentId = work.student.studentId,
                contact = work.student.contact,
                category = work.category,
                title = work.title,
                subtitle = work.subtitle,
                description = work.description,
                detailArtUrl = work.detailArt,
                thumbnailUrl = work.thumbnail,
                videoUrl = work.video,
            )
        }
    }
}
