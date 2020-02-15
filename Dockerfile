FROM adoptopenjdk/maven-openjdk11
COPY . /usr/app
WORKDIR /usr/app
RUN mvn compile
RUN mvn package
CMD [ "java", "-jar", "target/telenor-application-0.0.1-SNAPSHOT.jar"]
