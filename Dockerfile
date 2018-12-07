FROM openjdk:8-jdk-alpine
ADD target/ekar.jar ekar.jar
ENTRYPOINT ["java","-jar","/ekar.jar"]
EXPOSE 8080