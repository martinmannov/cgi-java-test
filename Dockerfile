FROM amazoncorretto:17
VOLUME /tmp
COPY target/cgi-loan-0.0.1.jar dockerimage.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/dockerimage.jar"]