# -
# Build Image
# -

FROM openjdk:8u171-jdk as BUILD

LABEL maintainer="hello@southcla.ws"

WORKDIR /economy
COPY . .
RUN ./gradlew build


# -
# Runtime Image
# -

FROM openjdk:8u171-jre

LABEL maintainer="hello@southcla.ws"

COPY --from=BUILD /economy/build/libs/Economy.jar /economy/run.jar
WORKDIR /economy

EXPOSE 80

CMD ["java", "-jar", "run.jar"]
