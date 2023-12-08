FROM openjdk:21-slim
WORKDIR /app
COPY build/libs/my-backend-0.0.1-SNAPSHOT.jar /app/app.jar
COPY src/main/resources/application.yml /app/application.yml
EXPOSE 80
CMD ["java", "-jar", "app.jar"]
