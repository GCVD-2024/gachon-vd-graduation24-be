package org.gcvd.server.domain.test.ui

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/test")
class TestController {
    @Hidden
    @GetMapping
    fun test(): String = "test"
}
