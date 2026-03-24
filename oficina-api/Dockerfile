
# ===== Stage 1: Build =====
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom and resolve dependencies first (better caching)
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn -B -q clean package spring-boot:repackage -DskipTests

# ===== Stage 2: Runtime =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Cria grupo/usuário de sistema (não-root) com IDs fixos
RUN groupadd -g 10001 appgroup \
    && useradd -r -u 10001 -g appgroup -s /usr/sbin/nologin -d /nonexistent appuser

# Copy the fat jar (Spring Boot) from build stage
COPY --from=build --chown=appuser:appgroup /app/target/oficina-api-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose default port
EXPOSE 8080

# Variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# (Opcional) Boas práticas da JVM em containers:
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0 -XX:+ExitOnOutOfMemoryError"

# A partir daqui, o processo roda como usuário não-root
USER appuser

# Run
ENTRYPOINT ["java","-jar","/app/app.jar"]
