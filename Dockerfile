# build
FROM adoptopenjdk/openjdk11:alpine as build-env
COPY ./ ./
RUN ../mvnw -q clean package -DskipTests=true

# run
FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=build-env ./main/target/main-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
