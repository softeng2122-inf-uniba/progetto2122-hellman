FROM openjdk:8-alpine
RUN mkdir /app
COPY ./build/libs/wordle-all.jar /app
RUN mkdir /app/src
ADD ./src /app/src
WORKDIR /app
ENTRYPOINT ["java", "-jar", "wordle-all.jar"]