package com.youcode.kingsleague.match_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class MatchServiceApplication

fun main(args: Array<String>) {
	runApplication<MatchServiceApplication>(*args)
}
