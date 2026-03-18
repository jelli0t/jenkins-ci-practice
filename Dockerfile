## Build stage
FROM bellsoft/liberica-runtime-container:jdk-21-stream-musl AS build

WORKDIR /workspace

# Copy only build configuration first for better layer caching
COPY gradlew .
COPY gradlew.bat .
COPY gradle gradle
COPY settings.gradle.kts .
COPY build.gradle.kts .

RUN chmod +x ./gradlew && ./gradlew --no-daemon -v

# Now copy the source
COPY src src

# Build the Spring Boot executable jar
RUN ./gradlew --no-daemon clean bootJar


## Runtime stage
FROM bellsoft/liberica-runtime-container:jre-21-musl AS runtime

WORKDIR /app

RUN useradd --create-home --uid 10001 appuser
USER 10001

COPY --from=build /workspace/build/libs/*.jar /app/app.jar

EXPOSE 8080

# Optional: pass JVM flags via JAVA_OPTS
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
