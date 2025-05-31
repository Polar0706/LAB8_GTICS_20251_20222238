FROM openjdk:17-slim

WORKDIR /app

ADD target/Lab6_20222238-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
