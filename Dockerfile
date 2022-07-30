# build
FROM maven:3.8.5 as builder
WORKDIR /usr/src/app
RUN apt-get update && apt-get install -y wget

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY . .
RUN mv -f ./infrastructure/src/main/resources/application-prod.yml ./infrastructure/src/main/resources/application.yml
RUN mvn install
RUN cp infrastructure/target/*.jar .

# package without maven
FROM openjdk:17-alpine
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/*.jar .
COPY --from=builder /usr/local/bin/dockerize /usr/local/bin/
ENTRYPOINT ["java", "-jar", "infrastructure-0.0.1-SNAPSHOT.jar"]