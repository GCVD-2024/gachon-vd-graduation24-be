package org.gcvd.server.domain.work.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "work")
data class Work(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_idx")
    val id: Long = 0L,
    @Column(name = "category", length = 20, nullable = false)
    val category: String,
    @Column(name = "title", length = 50, nullable = false)
    val title: String,
    @Column(name = "subtitle", length = 255, nullable = false)
    val subtitle: String,
    @Column(name = "description", length = 400, nullable = false)
    val description: String,
    @Column(name = "detail_art", length = 255, nullable = false)
    val detailArt: String,
    @Column(name = "thumbnail", length = 255, nullable = false)
    val thumbnail: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_idx")
    val student: Student,
)
