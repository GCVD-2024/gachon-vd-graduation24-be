package org.gcvd.server.domain.viewcount.ui

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.gcvd.server.common.code.ErrorStatus
import org.gcvd.server.common.response.ResponseDto
import org.gcvd.server.domain.viewcount.application.impl.ViewCountService
import org.gcvd.server.domain.viewcount.model.entity.ViewCount
import org.gcvd.server.domain.viewcount.ui.dto.ViewCountResponse
import org.springframework.http.ResponseCookie
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Tag(name = "ViewCount", description = "조회수 API")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController
@RequestMapping("api/viewcount")
class ViewCountController(
    private val viewCountService: ViewCountService,
) {
    @Operation(summary = "조회수 조회")
    @GetMapping("")
    fun getViewCount(): ResponseDto<ViewCountResponse.ViewCount> =
        ResponseDto.onSuccess(
            viewCountService.getViewCount(),
        )

    @Operation(
        summary = "조회수 증가",
        description = "중복 조회수 증가를 방지하기 위해, 최초 접속 이후 24시간 내 재접속으로 판단될 경우 조회수에 반영되지 않도록 하였습니다.",
    )
    @PostMapping("")
    fun addViewCount(
        req: HttpServletRequest,
        res: HttpServletResponse,
    ): ResponseDto<String> {
        if (isDuplicateUser(req, res)) {
            return ResponseDto.onFailure(
                code = ErrorStatus.BAD_REQUEST.code,
                message = ErrorStatus.DUPLICATE_USER.message,
            )
        } else {
            viewCountService.addViewCount()
            return ResponseDto.onSuccess(
                result = "조회수가 증가되었습니다.",
            )
        }
    }

    private fun isDuplicateUser(
        req: HttpServletRequest,
        res: HttpServletResponse,
    ): Boolean {
        var oldCookie: Cookie? = null

        val cookies = req.cookies
        if (cookies != null) {
            for (cookie in cookies) {
                if (cookie.name.equals(ViewCount.COOKIE_NAME)) {
                    oldCookie = cookie
                }
            }
        }

        val newCookie =
            ResponseCookie
                .from(ViewCount.COOKIE_NAME, UUID.randomUUID().toString())
                .path("/")
                .maxAge(60 * 60 * 24)
                .sameSite("None")
                .secure(true)
                .build()
        res.addHeader("Set-Cookie", newCookie.toString())

        return oldCookie != null
    }
}
