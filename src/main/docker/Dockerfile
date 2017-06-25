FROM hazz22/pi-jessie-java:oracle-jdk8

COPY app.jar /usr/src/app.jar
COPY application.properties /usr/src/application.properties
COPY log4j2.xml /usr/src/log4j2.xml

WORKDIR /usr/src/

EXPOSE 9000

HEALTHCHECK CMD curl --fail http://localhost:9000/health || exit 1

CMD ["java", "-jar", "app.jar"]
