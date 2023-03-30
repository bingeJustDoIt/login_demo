FROM centos:latest
WORKDIR /opt/software
ADD /target/app.jar app.jar
ENV JAVA_HOME="/opt/jdk1.8.0_361"
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
