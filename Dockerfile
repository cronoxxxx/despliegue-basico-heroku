# Stage 1: Build the application
# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copy only the necessary files for build to leverage Docker cache
COPY pom.xml .
COPY src src
COPY mvnw .
COPY .mvn .mvn

# Make mvnw executable and build
RUN chmod +x mvnw && \
    ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the built JAR (using the exact name is better than wildcard)
COPY --from=builder /app/target/*.jar app.jar

# Best practice: Run as non-root user
RUN useradd -m myuser && \
    chown -R myuser:myuser /app
USER myuser

EXPOSE 8080

# Better to use CMD unless you specifically need ENTRYPOINT
CMD ["java", "-jar", "app.jar"]
