FROM maven:3.8.3-openjdk-17 AS builder
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package


FROM openjdk:17-oracle
WORKDIR /app
COPY --from=builder /app/target/HackerNewsExtraPages-0.0.1-SNAPSHOT.jar /usr/local/lib/backend.jar
EXPOSE 8081 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/backend.jar"]