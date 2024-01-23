package com.youcode.kingsleague.gateway_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GatewayServiceApplication {
	@Bean
	fun dynamicRoutes(rdc: ReactiveDiscoveryClient, dlp: DiscoveryLocatorProperties): DiscoveryClientRouteDefinitionLocator {
		return DiscoveryClientRouteDefinitionLocator(rdc, dlp)
	}
}

fun main(args: Array<String>) {
	runApplication<GatewayServiceApplication>(*args)
}
