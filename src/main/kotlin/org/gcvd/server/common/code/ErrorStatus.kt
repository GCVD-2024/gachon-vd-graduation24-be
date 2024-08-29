package org.gcvd.server.common.code

import org.gcvd.server.common.response.ResponseDto
import org.springframework.http.HttpStatus

enum class ErrorStatus(
    val httpStatus: HttpStatus,
    val code: String,
    val message: String,
) : BaseErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_400", "잘못된 요청입니다."),
    METHOD_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "COMMON_400", "올바르지 않은 클라이언트 요청값입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403", "금지된 요청입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_405", "지원하지 않는 Http Method 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 에러가 발생했습니다."),
    ;

    override val reason: ResponseDto.ErrorReasonDto
        get() =
            ResponseDto.ErrorReasonDto(
                isSuccess = false,
                code = this.code,
                message = this.message,
            )

    override val reasonHttpStatus: ResponseDto.ErrorReasonDto
        get() =
            ResponseDto.ErrorReasonDto(
                httpStatus = this.httpStatus,
                isSuccess = false,
                code = this.code,
                message = this.message,
            )
}
