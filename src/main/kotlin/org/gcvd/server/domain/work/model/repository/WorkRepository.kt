package org.gcvd.server.domain.work.model.repository

import org.gcvd.server.domain.work.model.entity.Work
import org.springframework.data.jpa.repository.JpaRepository

interface WorkRepository : JpaRepository<Work, Long> {
    fun findAllByCategory(category: String): List<Work>

    fun findByStudent_StudentNameAndTitle(
        studentName: String,
        title: String,
    ): Work?
}
