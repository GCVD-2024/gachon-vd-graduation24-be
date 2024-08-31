package org.gcvd.server.domain.work.application.converter

import org.gcvd.server.domain.work.model.entity.Work
import org.gcvd.server.domain.work.ui.dto.WorkResponse

class WorkConverter {
    companion object {
        fun toWorkList(works: List<Work>): WorkResponse.WorkList {
            if (works.isEmpty()) {
                return WorkResponse.WorkList(emptyList())
            }

            val workList =
                works.map {
                    WorkResponse.WorkInfo(
                        title = it.title,
                        studentName = it.student.studentName,
                        thumbnailUrl = it.thumbnail,
                    )
                }
            return WorkResponse.WorkList(workList)
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
            )
        }
    }
}
