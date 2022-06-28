# 1) docker build -t web .
# 2) docker run -d -p 8080:8080 -t web:latest
# 3) heroku container:push web
# 3) heroku container:release web
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=application/target/application-1.0-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE $PORT
ENTRYPOINT ["java","-jar","app.jar"]