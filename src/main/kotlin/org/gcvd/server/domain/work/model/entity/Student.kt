package org.gcvd.server.domain.work.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "student")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_idx")
    val id: Long = 0L,
    @Column(name = "id", length = 10, nullable = false)
    val studentId: String,
    @Column(name = "name", length = 30, nullable = false)
    val studentName: String,
    @Column(name = "contact", length = 50, nullable = false)
    val contact: String,
)
