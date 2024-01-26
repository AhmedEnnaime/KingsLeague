package com.youcode.kingsleague.organizer_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
class OrganizerServiceApplication

fun main(args: Array<String>) {
	runApplication<OrganizerServiceApplication>(*args)
}
