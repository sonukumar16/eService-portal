FROM maven:3.6.2-ibmjava-8-alpine as build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:8u282-oraclelinux8

WORKDIR /app

COPY --from=build /app/target/eService-portal-0.0.1-SNAPSHOT.jar /app/eService-portal-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar,"eService-portal-0.0.1-SNAPSHOT.jar"]
