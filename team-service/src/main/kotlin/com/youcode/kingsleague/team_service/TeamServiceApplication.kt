package com.youcode.kingsleague.team_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class TeamServiceApplication

fun main(args: Array<String>) {
	runApplication<TeamServiceApplication>(*args)
}
