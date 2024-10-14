package org.gcvd.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }
}
