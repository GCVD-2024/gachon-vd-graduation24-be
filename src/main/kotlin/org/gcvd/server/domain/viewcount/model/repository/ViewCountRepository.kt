package org.gcvd.server.domain.viewcount.model.repository

import org.gcvd.server.domain.viewcount.model.entity.ViewCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface ViewCountRepository : JpaRepository<ViewCount, Long> {
    @Modifying
    @Query("UPDATE ViewCount v SET v.count = v.count + :increment WHERE v.name = :name")
    fun updateCount(
        name: String,
        increment: Int,
    )

    fun existsByName(name: String): Boolean

    fun getReferenceByName(name: String): ViewCount
}
