package org.gcvd.server.common.code

import org.gcvd.server.common.response.ResponseDto
import org.springframework.http.HttpStatus

enum class SuccessStatus(
    val httpStatus: HttpStatus? = null,
    val code: String,
    val message: String,
) : BaseCode {
    // Success
    OK(HttpStatus.OK, "SUCCESS_200", "OK"),
    ;

    override val reason: ResponseDto.ReasonDto
        get() =
            ResponseDto.ReasonDto(
                isSuccess = true,
                code = this.code,
                message = this.message,
            )

    override val reasonHttpStatus: ResponseDto.ReasonDto
        get() =
            ResponseDto.ReasonDto(
                httpStatus = this.httpStatus,
                isSuccess = true,
                code = this.code,
                message = this.message,
            )
}
