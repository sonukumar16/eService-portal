FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /app
COPY . .
RUN mvn clean package
COPY  /app/target/eservice.jar /app/eservice.jar
ENTRYPOINT ["java","-jar,"eservice.jar"]
