FROM maven:3.9.9-eclipse-temurin-21-alpine as maven-builder
# Copy the source code and pom.xml
COPY ./target/AFAC-0.0.1-SNAPSHOT.jar  app.jar

# Set default environment variables
ENV SERVER_PORT=8080


# Command to run the application
ENTRYPOINT ["java","-jar","AFAC-0.0.1-SNAPSHOT.jar"]