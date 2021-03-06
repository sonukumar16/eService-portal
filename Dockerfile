FROM openjdk:8u282-oraclelinux8

WORKDIR /app

COPY . .

ENTRYPOINT ["java","-jar", "/app/target/eService-portal-0.0.1-SNAPSHOT.jar"]
