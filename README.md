# Camel-Kafka-connector demo

## Introduction

This is an example for Infinispan-kafka connector

## What is needed

- An Artemis Broker 2.9.0 running
- A Kafka Cluster 2.4.0 running 
- An AWS S3 bucket

## Running

```
mvn clean package
export CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')"
$KAFKA_HOME/bin/connect-standalone.sh $KAFKA_HOME/config/connect-standalone.properties config/CamelAWSS3..
$KAFKA_HOME/bin/connect-standalone.sh $KAFKA_HOME/config/connect-standalone.properties config/CamelJms..
```
