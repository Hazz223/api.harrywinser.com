FROM hazz22/pi-openjre

ADD app.jar /usr/src/app.jar
ADD application.properties /user/src/application.properties
ADD log4j2.xml /user/src/log4j2.xml

WORKDIR /usr/src/

RUN sh -c 'touch app.jar'

EXPOSE 9000

CMD ["java", "-jar", "app.jar"]
