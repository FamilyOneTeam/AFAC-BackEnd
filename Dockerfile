# Build stage
FROM maven:3.9.9-eclipse-temurin-21-alpine AS maven-builder

# Set working directory
WORKDIR /app

# Copy the source code and pom.xml
COPY src ./src
COPY pom.xml .

# Build the application
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app-service

# Copy the built JAR file
COPY --from=maven-builder /app/target/*.jar ./app.jar

# Environment variables
ENV SERVER_PORT=8081
ENV SPRING_PROFILES_ACTIVE=prod

# Expose the port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]