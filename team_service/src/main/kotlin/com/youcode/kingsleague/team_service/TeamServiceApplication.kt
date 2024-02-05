package com.youcode.kingsleague.team_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class TeamServiceApplication

fun main(args: Array<String>) {
	runApplication<TeamServiceApplication>(*args)
}
