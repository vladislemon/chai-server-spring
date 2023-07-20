FROM bellsoft/liberica-openjre-alpine-musl:17
WORKDIR /app
COPY build/libs/*.jar application.jar
ENTRYPOINT java -jar application.jar
