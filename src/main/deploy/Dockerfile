FROM openjdk:8u151-jre-alpine

COPY app.jar /usr/src/app.jar
COPY application.properties /usr/src/application.properties
COPY log4j2.xml /usr/src/log4j2.xml

WORKDIR /usr/src/

EXPOSE 3000

CMD ["java", "-jar", "app.jar"]
