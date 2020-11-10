FROM openjdk:11-jdk-slim as build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw package -DskipTests

FROM openjdk:11-jre-slim
ARG DEPENDENCY=/app/target/
COPY --from=build ${DEPENDENCY}/ /app/target
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:postgresql://db:5432/prismo-backend-api","-jar","/app/target/prismo-0.0.1-SNAPSHOT.jar"]