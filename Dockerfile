FROM openjdk:11.0.1-jre-slim

ARG JAR_FILE
ADD ${JAR_FILE} app.jar

VOLUME /tmp
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
