package com.youcode.kingsleague.matchday_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class MatchDayServiceApplication

fun main(args: Array<String>) {
	runApplication<MatchDayServiceApplication>(*args)
}
