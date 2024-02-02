# # Stage 1: Build the application
# FROM openjdk:17-jdk-alpine AS build
# WORKDIR /app
# COPY . .
# RUN ./gradlew build

# Stage 2: Create a smaller image with only the JAR file
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar /app/findAGymBro-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "findAGymBro-0.0.1-SNAPSHOT.jar"]
