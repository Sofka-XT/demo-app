# 1) docker build -t scoreextraction .
# 2) docker run -d -p 8080:8080 -t scoreextraction:latest
# 3) heroku container:push scoreextraction
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=application/target/application-1.0-SNAPSHOT.jar

WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]