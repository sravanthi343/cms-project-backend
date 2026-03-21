# Use official OpenJDK 17 base image
# Use official OpenJDK 17 slim image (LTS)
FROM openjdk:17-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (to cache dependencies)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy the source code
COPY src src

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the Spring Boot app (skip tests to speed up)
RUN ./mvnw clean package -DskipTests

# Expose the port (Render will provide $PORT)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","target/complaint-management-system-0.0.1-SNAPSHOT.jar"]