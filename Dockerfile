FROM phenompeople/openjdk17:latest
COPY target/EventTrackingSystem.jar /EventTrackingSystem.jar
ENTRYPOINT ["java","-jar","/EventTrackingSystem.jar"]
EXPOSE 8080