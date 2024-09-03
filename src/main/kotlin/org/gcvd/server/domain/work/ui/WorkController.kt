package org.gcvd.server.domain.work.ui

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.gcvd.server.common.response.ResponseDto
import org.gcvd.server.domain.work.application.impl.WorkService
import org.gcvd.server.domain.work.ui.dto.WorkResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Work", description = "작품 API")
@RestController
@RequestMapping("api/work")
class WorkController(
    private val workService: WorkService,
) {
    /**
     * Todo
     * request : page, category
     * response : 페이지번호, 총페이지수, 전체개수, 10개 데이터
     */
    @Operation(summary = "카테고리별 작업물 리스트 조회")
    @GetMapping("")
    fun getWorksList(
        @Schema(description = "ALL / UX / Brand / Graphic / Illust / Media") @RequestParam category: String,
        @Schema(description = "페이지 번호") @RequestParam currentPage: Int,
    ): ResponseDto<WorkResponse.WorkList> = ResponseDto.onSuccess(workService.getWorksList(category, currentPage))

    @Operation(summary = "작업물 상세 내용 조회")
    @GetMapping("/{name}/{title}")
    fun getDetailWork(
        @Schema(description = "학생명") @PathVariable name: String,
        @Schema(description = "작품명") @PathVariable title: String,
    ): ResponseDto<WorkResponse.DetailWork> = ResponseDto.onSuccess(workService.getDetailWork(name, title))
}
