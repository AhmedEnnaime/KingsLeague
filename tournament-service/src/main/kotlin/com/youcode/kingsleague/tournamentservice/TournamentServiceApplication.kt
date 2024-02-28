package com.youcode.kingsleague.tournamentservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@ComponentScan("com.youcode.kingsleague.common.config")
class TournamentServiceApplication

fun main(args: Array<String>) {
    runApplication<TournamentServiceApplication>(*args)
}
