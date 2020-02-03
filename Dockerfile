FROM maven:3.5-jdk-8-alpine AS build-env
WORKDIR /app
COPY . .
RUN mvn clean package
########################
FROM openjdk:8-jre-alpine
EXPOSE 8080
RUN TZ=Europe/Berlin
RUN apk add --update tzdata && rm -rf /var/cache/apk/*
RUN mkdir -p /root/.m2 && chmod -R 777 /root/.m2 
WORKDIR /usr/local/app/
COPY  --from=build-env /app/target/*.jar  /usr/local/app/
RUN mv /usr/local/app/job-management*.jar /usr/local/app/job-management.jar
CMD ["sh", "-c", "java ${JVM_OPTS}  -jar /usr/local/app/job-management.jar"]
