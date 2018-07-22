# -
# Build Image
# -

LABEL maintainer="hello@southcla.ws"

FROM openjdk:8u171-jdk as BUILD

WORKDIR /economy
COPY . .
RUN ./gradlew build


# -
# Runtime Image
# -

FROM openjdk:8u171-jre

COPY --from=BUILD /economy/build/libs/Economy.jar /economy/run.jar
WORKDIR /economy

EXPOSE 80

CMD ["java", "-jar", "run.jar"]
