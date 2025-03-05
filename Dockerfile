# Primera etapa: Build
FROM eclipse-temurin:21-jdk-alpine as maven-builder

WORKDIR /app
COPY pom.xml .
COPY src src
COPY mvnw .
COPY .mvn .mvn
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Segunda etapa: Runtime
FROM eclipse-temurin:21-jdk-alpine

# Copia el JAR desde la etapa de build
COPY --from=maven-builder /app/target/AFAC-0.0.1-SNAPSHOT.jar app.jar

# Set default environment variables
ENV SERVER_PORT=8081
ENV SPRING_PROFILES_ACTIVE=prod

# Command to run the application
ENTRYPOINT ["java","-jar","app.jar"]