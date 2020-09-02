FROM maven:3-openjdk-8-slim as builder

WORKDIR /app
COPY pom.xml ./
COPY src/ src/

RUN mvn package

FROM gcr.io/spark-operator/spark:v3.0.0-gcs-prometheus

# FiX broken bigquery dependency
RUN rm -rf /opt/spark/jars/spark-bigquery-latest.jar

COPY --from=builder /app/target/montecarlo-pi-0.0.1.jar .

COPY log4j.properties /opt/spark/conf/

ENV NUM=10
ENV URL="montecarlo.csv"

CMD [ "/opt/spark/bin/spark-submit", "--class", "simulation.App", "montecarlo-pi-0.0.1.jar" ]