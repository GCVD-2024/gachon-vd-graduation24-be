package org.gcvd.server.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedOriginPatterns(
                "http://localhost:5173",
                "gcvd2024-dev.vercel.app",
                "https://www.gcvd2024.com",
                "https://gcvd2024.com",
            ).allowedMethods("GET", "POST", "OPTIONS")
            .allowCredentials(true)
    }
}
