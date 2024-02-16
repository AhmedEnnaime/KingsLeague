package com.youcode.kingsleague.tournamentteamsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class TournamentTeamsServiceApplication

fun main(args: Array<String>) {
	runApplication<TournamentTeamsServiceApplication>(*args)
}
