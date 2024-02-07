package com.youcode.kingsleague.round_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class RoundServiceApplication

fun main(args: Array<String>) {
	runApplication<RoundServiceApplication>(*args)
}
