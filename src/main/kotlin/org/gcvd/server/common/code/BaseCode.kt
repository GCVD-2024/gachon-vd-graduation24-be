package org.gcvd.server.common.code

import org.gcvd.server.common.response.ResponseDto

interface BaseCode {
    val reason: ResponseDto.ReasonDto

    val reasonHttpStatus: ResponseDto.ReasonDto
}
