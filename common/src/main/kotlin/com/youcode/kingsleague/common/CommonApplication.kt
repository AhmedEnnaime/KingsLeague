package com.youcode.kingsleague.common

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CommonApplication

fun main(args: Array<String>) {
	runApplication<CommonApplication>(*args)
}
