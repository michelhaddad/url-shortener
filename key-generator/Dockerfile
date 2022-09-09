FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE
ENV JAR_FILE=${JAR_FILE}

COPY target/$JAR_FILE $JAR_FILE

ENTRYPOINT java -jar $JAR_FILE