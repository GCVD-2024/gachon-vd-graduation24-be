package org.gcvd.server.common.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.swagger.v3.oas.annotations.Hidden
import org.gcvd.server.common.code.SuccessStatus
import org.springframework.http.HttpStatus

@Hidden
@JsonPropertyOrder("isSuccess", "code", "message", "result")
data class ResponseDto<T>(
    @field:JsonProperty("isSuccess")
    private val isSuccess: Boolean,
    @field:JsonProperty("code")
    private val code: String,
    @field:JsonProperty("message")
    private val message: String,
    @field:JsonProperty("result")
    private val result: T,
) {
    data class ErrorReasonDto(
        val httpStatus: HttpStatus? = null,
        val isSuccess: Boolean = false,
        val code: String,
        val message: String,
    )

    data class ReasonDto(
        val httpStatus: HttpStatus? = null,
        val isSuccess: Boolean = false,
        val code: String,
        val message: String,
    )

    companion object {
        // 성공한 경우
        fun <T> onSuccess(result: T): ResponseDto<T> =
            ResponseDto(
                isSuccess = true,
                code = SuccessStatus.OK.code,
                message = SuccessStatus.OK.message,
                result = result,
            )
    }
}
