package org.gcvd.server.common.code

import org.gcvd.server.common.response.ResponseDto

interface BaseErrorCode {
    val reason: ResponseDto.ErrorReasonDto
    val reasonHttpStatus: ResponseDto.ErrorReasonDto
}
