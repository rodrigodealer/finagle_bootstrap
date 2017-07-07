FROM openjdk:8u131-jdk-alpine

COPY target/scala-2.12/app.jar /opt/app.jar

CMD ["java", "-jar", "/opt/app.jar"]
