package com.youcode.kingsleague.team_service.config

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Value;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
class OpenAPIConfig {

    @Value("\${team_service.openapi.dev-url:http://localhost:8083}")
    private val devUrl: String? = null

    @Bean
    fun myOpenAPI(): OpenAPI {
        val devServer: Server = Server()
        devServer.url = devUrl
        devServer.description = "Server URL in Development environment"

        val contact: Contact = Contact()
        contact.email = "ahmedennaime20@gmail.com"
        contact.name = "AhmedEnnaime"

        val info: Info = Info()
            .title("Team Service")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints for a team microservice in kingsLeague project.")

        return OpenAPI().info(info).servers(listOf(devServer))
    }
}
