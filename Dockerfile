FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/quickquid-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
