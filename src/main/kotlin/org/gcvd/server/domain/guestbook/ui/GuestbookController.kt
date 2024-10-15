package org.gcvd.server.domain.guestbook.ui

import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.gcvd.server.common.response.ResponseDto
import org.gcvd.server.domain.guestbook.application.impl.GuestbookService
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookRequest
import org.gcvd.server.domain.guestbook.ui.dto.GuestbookResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Guestbook", description = "방명록 API")
@RestController
@RequestMapping("api/guestbook")
class GuestbookController(
    private val guestbookService: GuestbookService,
) {
    @Operation(summary = "방명록 조회")
    @GetMapping()
    fun getGuestBookList(): ResponseDto<GuestbookResponse.GuestbookList> =
        ResponseDto.onSuccess(guestbookService.getGuestBookList())

    @Operation(summary = "방명록 등록")
    @PostMapping()
    fun createGuestBook(
        @RequestBody request: GuestbookRequest.CreateGuestBook,
    ): ResponseDto<Nothing?> {
        guestbookService.createGuestBook(request)
        return ResponseDto.onSuccess(null)
    }

    @Hidden
    @PostMapping("/clear")
    fun clearCache(): ResponseDto<Boolean> = ResponseDto.onSuccess(guestbookService.clearCache())
}
