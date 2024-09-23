package org.gcvd.server.domain.work.ui.dto

class WorkResponse {
    data class WorkList(
        val currentPage: Int,
        val totalPage: Int,
        val totalWorks: Int,
        val works: List<WorkInfo>,
    )

    data class WorkInfo(
        val category: String,
        val studentName: String,
        val title: String,
        val thumbnailUrl: String,
    )

    data class DetailWork(
        val studentName: String,
        val studentId: String,
        val contact: String,
        val category: String,
        val title: String,
        val subtitle: String,
        val description: String,
        val detailArtUrl: String,
        val thumbnailUrl: String,
        val videoUrl: String?,
    )

    companion object {
        fun emptyDetailWork(): DetailWork =
            DetailWork(
                studentName = "",
                studentId = "",
                contact = "",
                category = "",
                title = "",
                subtitle = "",
                description = "",
                detailArtUrl = "",
                thumbnailUrl = "",
                videoUrl = "",
            )
    }
}
