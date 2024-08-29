package org.gcvd.server.domain.guestbook.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "guestbook")
data class Guestbook(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guestbook_idx")
    val id: Long = 0L,
    @Column(name = "nickname", length = 30, nullable = false)
    val nickname: String,
    @Column(name = "content", length = 255, nullable = false)
    val content: String,
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null,
)