FROM gradle:7.5-jdk17 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-slim as final
ARG JAR_FILE=/home/gradle/src/build/libs/*.jar
COPY --from=builder ${JAR_FILE} vital-donation-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "vital-donation-api.jar"]