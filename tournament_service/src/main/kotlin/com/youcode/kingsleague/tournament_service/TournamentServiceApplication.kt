package com.youcode.kingsleague.tournament_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class TournamentServiceApplication

fun main(args: Array<String>) {
	runApplication<TournamentServiceApplication>(*args)
}
