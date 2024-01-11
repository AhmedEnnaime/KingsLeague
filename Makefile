run:
	@docker compose up -d
down:
	@docker compose down
build:
	./gradlew build
test:
	./gradlew test
