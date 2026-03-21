# Use official OpenJDK 17 image (full version for compatibility)
FROM openjdk:17

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (cache dependencies)
COPY pom.xml .
COPY .mvn/ .mvn
COPY mvnw .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Copy source code
COPY src ./src

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Set entry point to run the jar
ENTRYPOINT ["java","-jar","target/*.jar"]