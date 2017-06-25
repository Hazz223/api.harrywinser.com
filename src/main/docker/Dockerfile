FROM hazz22/pi-jessie-java:oracle-jdk8

ADD app.jar /usr/src/app.jar
ADD application.properties /usr/src/application.properties
ADD log4j2.xml /usr/src/log4j2.xml

WORKDIR /usr/src/

RUN sh -c 'touch app.jar'

EXPOSE 9000

HEALTHCHECK CMD curl --fail http://localhost:9000/health || exit 1

CMD ["java", "-jar", "app.jar"]
