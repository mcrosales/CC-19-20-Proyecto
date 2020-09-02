FROM maven:3.5-jdk-8-alpine
LABEL "maintainer"="mariocrosl@gmail.com"
EXPOSE 8081/tcp
WORKDIR /app
COPY  / /app
RUN mvn package
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENTRYPOINT ["java","-jar","target/statistics-0.1.jar"]
