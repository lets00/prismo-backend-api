FROM openjdk:11-jdk-slim as build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B
COPY src src
# Package the application
RUN ./mvnw package -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11-jre-slim
ARG DEPENDENCY=/app/target/
# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/ /app/target
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:postgresql://db:5432/prismo-backend-api","-Dspring.datasource.username=postgres","-Dspring.datasource.password=p@ssw0rd","-jar","/app/target/prismo-0.0.1-SNAPSHOT.jar"]