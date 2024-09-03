package org.gcvd.server.domain.work.model.repository

import org.gcvd.server.domain.work.model.entity.Work
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface WorkRepository : JpaRepository<Work, Long> {
    override fun findAll(pageable: Pageable): Page<Work>

    fun findAllByCategory(
        category: String,
        pageable: Pageable,
    ): Page<Work>

    fun findByStudent_StudentNameAndTitle(
        studentName: String,
        title: String,
    ): Work?
}
