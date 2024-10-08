FROM eclipse-temurin:21-jdk
WORKDIR /src
COPY target/java-group-project-0.0.1-SNAPSHOT.jar /src/java-group.project.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library-service.jar"]