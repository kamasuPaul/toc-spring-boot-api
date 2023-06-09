FROM openjdk:19-alpine
EXPOSE 10093
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

#RUN apk add --no-cache tzdata
#ENV TZ=Africa/Kampala

ENTRYPOINT ["java","-jar","/app.jar"]