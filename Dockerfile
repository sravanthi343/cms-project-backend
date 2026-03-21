# Use Render-supported Java 17 image
FROM ghcr.io/codexia/render-java:17

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

# Run the Spring Boot JAR
ENTRYPOINT ["java","-jar","target/*.jar"]