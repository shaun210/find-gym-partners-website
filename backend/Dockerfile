# Stage 1: Build the application
FROM openjdk:17-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

# Stage 2: Create a smaller image with only the JAR file
FROM openjdk:17-jdk-alpine AS production
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/findAGymBro.jar
EXPOSE 8080
CMD ["java", "-jar", "findAGymBro.jar"]