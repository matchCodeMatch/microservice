FROM amazoncorretto:11-alpine-jdk
EXPOSE 8081
ADD build/libs/demo-0.0.2-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","/demo.jar"]