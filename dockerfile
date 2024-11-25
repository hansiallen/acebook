# Stage 1: Build the Spring Boot app using Maven with OpenJDK 21
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Set the working directory for Maven build
WORKDIR /app

# Copy the pom.xml and source code to the container (to leverage Docker cache)
COPY pom.xml .
COPY src ./src

# Run Maven build to create the JAR file (skip tests for faster builds in production)
RUN mvn clean package -DskipTests

# Stage 2: Use OpenJDK 21 as the runtime image
FROM openjdk:21-jdk-slim

# Set the working directory inside the runtime container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/acebook-template-1.0-SNAPSHOT.jar app.jar

# Expose the port the app will run on (default Spring Boot port is 8080)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
