FROM dockerfile/java:oracle-java8

VOLUME /tmp
ADD docker-example.jar /opt/docker-example/
EXPOSE 8080
WORKDIR /opt/docker-example/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "docker-example.jar"]
