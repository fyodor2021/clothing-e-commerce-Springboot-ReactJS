FROM gradle:8-jdk17-alpine AS builder
COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build -x test


FROM  openjdk:17-alpine

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/order-microservice.jar

RUN mkdir /gcb

COPY  gifted-proxy-427415-p2-69f656e027c6.json /gcb/gifted-proxy-427415-p2-69f656e027c6.json

EXPOSE 3002

ENTRYPOINT ["java", "-jar", "/app/order-microservice.jar"]
