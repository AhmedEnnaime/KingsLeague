run-dind: 
	@docker run \
  --name jenkins-docker \
  --rm \
  --detach \
  --privileged \
  --network jenkins \
  --network-alias docker \
  --env DOCKER_TLS_CERTDIR=/certs \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-data:/var/jenkins_home \
  --publish 2376:2376 \
  docker:dind \
  --storage-driver overlay2

build-jenkins: 
	@docker build -t myjenkins-blueocean:2.440.2-1 .

run-jenkins:
	@docker run \
  --name jenkins-blueocean \
  --restart=on-failure \
  --detach \
  --network jenkins \
  --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client \
  --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 \
  --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  myjenkins-blueocean:2.440.2-1

run:
	@./gradlew build -x test
	docker compose -f config_service/docker-compose.yml up --build -d; \
	docker compose -f eureka_server/docker-compose.yml up --build -d; \
	docker compose -f gateway_service/docker-compose.yml up --build -d; \
	docker compose -f tournament-service/docker-compose.yml up --build -d; \
	docker compose -f team-service/docker-compose.yml up --build -d; \
	docker compose -f tournamentTeams-service/docker-compose.yml up --build -d; \
	docker compose -f round-service/docker-compose.yml up --build -d; \
	docker compose -f matchDay-service/docker-compose.yml up --build -d; \
	docker compose -f match-service/docker-compose.yml up --build -d; \

run-eureka:
	@docker compose -f eureka_server/docker-compose.yml up --build -d
run-config:
	@docker compose -f config_service/docker-compose.yml up --build -d
run-gateway:
	@docker compose -f gateway_service/docker-compose.yml up --build -d
run-tournament:
	@docker compose -f tournament-service/docker-compose.yml up --build -d
run-team:
	@docker compose -f team-service/docker-compose.yml up --build -d
run-tournamentTeams:
	@docker compose -f tournamentTeams-service/docker-compose.yml up --build -d
run-round:
	@docker compose -f round-service/docker-compose.yml up --build -d
run-matchDay:
	@docker compose -f matchDay-service/docker-compose.yml up --build -d
run-match:
	@docker compose -f match-service/docker-compose.yml up --build -d

down-eureka:
	@docker compose -f eureka_server/docker-compose.yml down

down-config:
	@docker compose -f config_service/docker-compose.yml down

down-gateway:
	@docker compose -f gateway_service/docker-compose.yml down

down-tournament:
	@docker compose -f tournament-service/docker-compose.yml down

down-team:
	@docker compose -f team-service/docker-compose.yml down

down-tournamentTeams:
	@docker compose -f tournamentTeams-service/docker-compose.yml down

down-round:
	@docker compose -f round-service/docker-compose.yml down

down-matchDay:
	@docker compose -f matchDay-service/docker-compose.yml down

down-match:
	@docker compose -f match-service/docker-compose.yml down

down:
	@docker compose -f config_service/docker-compose.yml down; \
	 docker compose -f eureka_server/docker-compose.yml down; \
	 docker compose -f gateway_service/docker-compose.yml down; \
	 docker compose -f tournament-service/docker-compose.yml down; \
	 docker compose -f team-service/docker-compose.yml down; \
	 docker compose -f tournamentTeams-service/docker-compose.yml down; \
	 docker compose -f round-service/docker-compose.yml down; \
	 docker compose -f matchDay-service/docker-compose.yml down; \
	 docker compose -f match-service/docker-compose.yml down;

build:
	./gradlew build -x test
test:
	./gradlew test
