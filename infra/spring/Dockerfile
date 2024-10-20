# Build stage
FROM maven:3.9.9-eclipse-temurin-23-alpine AS build
WORKDIR /home/app
COPY src ./src
COPY pom.xml .

# Compile project
RUN mvn clean package -DskipTests

# Run application
FROM eclipse-temurin:23-jdk-alpine
WORKDIR /home/app
COPY --from=build /home/app/target/*.jar /usr/local/lib/spring.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/spring.jar"]