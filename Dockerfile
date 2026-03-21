# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package the application (skipping tests for speed)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim 
WORKDIR /app

# Copy the built jar from the build stage
# Note: Ensure the jar name matches what Maven generates in /target
COPY --from=build /app/target/*.jar app.jar

# Expose the port Spring Boot runs on (default is 8080)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]