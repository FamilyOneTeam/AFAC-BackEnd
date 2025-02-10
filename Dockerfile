# Primera etapa: Build
FROM eclipse-temurin:21-jdk-alpine as maven-builder

WORKDIR /app
COPY pom.xml .
COPY src src
COPY mvnw .
COPY .mvn .mvn
COPY . . https://github.com/FamilyOneTeam/AFAC-BackEnd.git/
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Segunda etapa: Runtime
FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp

# Copia el JAR desde la etapa de build
COPY --from=maven-builder /app/target/AFAC-0.0.1-SNAPSHOT.jar app.jar

# Set default environment variables
ENV SERVER_PORT=8081

# Command to run the application
ENTRYPOINT ["java","-jar","app.jar"]