# Use official OpenJDK 17 full version (guaranteed to exist)
FROM openjdk:17.0.9-jdk

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

# Build Spring Boot app
RUN ./mvnw clean package -DskipTests

# Set entry point
ENTRYPOINT ["java","-jar","target/*.jar"]