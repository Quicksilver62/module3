FROM gradle:8.14-jdk21 AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle ./gradle
RUN gradle dependencies --no-daemon

COPY src ./src
RUN gradle build --no-daemon -x test

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/build/libs/order-service-*.jar app.jar

EXPOSE 9005

ENTRYPOINT ["java", "-jar", "app.jar"]