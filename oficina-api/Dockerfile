
# ===== Stage 1: Build =====
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom and resolve dependencies first (better caching)
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn -B -q clean package -DskipTests

# ===== Stage 2: Runtime =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the fat jar (Spring Boot) from build stage
COPY --from=build /app/target/oficina-api-0.0.1-SNAPSHOT.jar app.jar

# Expose default port
EXPOSE 8080

# Default profile (basic)
ENV SPRING_PROFILES_ACTIVE=""

# Run
ENTRYPOINT ["java","-jar","/app/app.jar"]
