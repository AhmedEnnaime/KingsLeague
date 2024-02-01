package com.youcode.kingsleague.organizer_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@ComponentScan("com.youcode.kingsleague.common_service")
class OrganizerServiceApplication

fun main(args: Array<String>) {
	runApplication<OrganizerServiceApplication>(*args)
}
