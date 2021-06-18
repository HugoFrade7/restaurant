FROM maven:3.6-jdk-11-slim as builder

WORKDIR app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src
RUN mvn package
RUN mv target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot

LABEL maintainer=hugo.frade@25friday.com

WORKDIR app

COPY --from=builder app/dependencies/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]