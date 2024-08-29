package org.gcvd.server.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI()
            .addServersItem(Server().url("/"))
            .info(
                Info()
                    .title("gcvd Server")
                    .version("1.0")
                    .description("gcvd Server API"),
            )
}
