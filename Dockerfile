FROM gradle:jdk8 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 


FROM java:8
EXPOSE 8103
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT [ "java", "-jar", "/app/spring-boot-application.jar" ]
