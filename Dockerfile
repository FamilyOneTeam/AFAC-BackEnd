# Build stage
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/AFAC-0.0.1-SNAPSHOT.jar ./app.jar

# Environment variables
ENV SERVER_PORT=8081
ENV SPRING_PROFILES_ACTIVE=prod

# Command
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]