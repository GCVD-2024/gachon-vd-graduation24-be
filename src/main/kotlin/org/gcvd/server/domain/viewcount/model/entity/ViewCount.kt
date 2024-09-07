package org.gcvd.server.domain.viewcount.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "view_count")
data class ViewCount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_count_idx")
    val id: Long = 0L,
    @Column(name = "name", length = 50, unique = true, nullable = false)
    val name: String,
    @Column(name = "count", nullable = false)
    val count: Int = 0,
) {
    companion object {
        const val VIEW_COUNT_KEY: String = "view_count"
        const val COOKIE_NAME: String = "viewed_user"
    }
}
