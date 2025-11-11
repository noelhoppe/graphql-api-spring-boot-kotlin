FROM gradle:8.14.3 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

FROM openjdk:26-ea-oracle
WORKDIR /app
COPY --from=build /app/build/libs/*SNAPSHOT.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]