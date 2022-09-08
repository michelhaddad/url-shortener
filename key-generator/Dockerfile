FROM openjdk:11
# TODO: Find image with smaller size

ARG JAR_FILE
ENV JAR_FILE=${JAR_FILE}

COPY target/$JAR_FILE $JAR_FILE

ENTRYPOINT java -jar $JAR_FILE