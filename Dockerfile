FROM maven:3.5-jdk-8-alpine
LABEL "maintainer"="mariocrosl@gmail.com"
EXPOSE 8080/tcp
WORKDIR /app
COPY  /vendors_products /app
RUN mvn package
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENTRYPOINT ["java","-jar","target/vendors_products-0.1.jar"]
