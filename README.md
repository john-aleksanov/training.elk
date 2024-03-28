# The Elastic stack demo project

## Overview

This project demonstrates the power and flexibility of the Elastic Stack (Elasticsearch, Logstash, Kibana, and Filebeat) for log management
and analysis. It's designed to simulate a real-world scenario where a Java application generates log events, which are then processed and
visualized through the Elastic stack. The project uses Docker containers to locally run each component of the Elastic Stack that are
configured to work together.

## Architecture

The project architecture consists of four main components:

- **Elasticsearch**: The heart of the Elastic Stack, used for storing, searching, and analyzing large volumes of data quickly and in near
  real-time. In our case - application logs.
- **Logstash**: The data processing component of the Elastic Stack that ingests data from various sources, transforms it, and then sends it
  to Elasticsearch. In our case, it parses log data sent by Filebeat and sends to Elasticsearch for persistence and further retrieval.
- **Kibana**: A web interface for Elasticsearch that allows users to visualize the data stored in Elasticsearch indices.
- **Filebeat**: A lightweight shipper for forwarding and centralizing log data. In this project, Filebeat monitors the [./logs/](./logs)
  directory for log files generated or updated by the Java application and sends them to Logstash.

## Prerequisites

- Docker and Docker Compose: For running the Elastic stack.
- JDK: Required to compile and run the Java application.
- Gradle: Used for building the Java project.

## Component setup

Simply clone the repository to your machine and run

```shell
docker-compose up
```

in the root of the project's directory. This will spin up four docker containers with the four main components described above.

## Log generation

After the components are up and running, compile and run the Java application, either directly from your favorite IDE or from the terminal:

```shell
gradle run
```

The application will use Log4J2 to generate 5000 log events, which will be logged to rotating log files in the [./logs](./logs) directory.

## View Logs in Kibana

Once the application has generated the logs, and Filebeat has processed and sent them to Elasticsearch via Logstash, you can view the logs
in Kibana. Open a web browser and navigate to http://localhost:5601, then to the "Discover" page, and create a data view with a
`my-logs*` pattern that will comprise all logs sent to Elasticsearch by Logstash.

## Project Structure

- **compose.yml**: Docker Compose file to start the Elastic Stack services.
- **./config/**: Contains configuration files for Logstash and Filebeat.
- **src/main/java/**: Contains the Java application source code.
- **src/main/resources/log4j2.xml**: Log4j2 configuration file for the Java application.
- **./logs/**: Directory where the Java application logs are stored.
- **./.elasticdata, ./.logstashdata, ./.filebeatdata, and ./.kibanadata**: Directories for persistent storage of Elastic stack data that will
  be created by the four components.

## Cleanup

1. Stop and remove the Docker containers:

```shell
docker-compose down
```

2. Remove the local directories for Docker bind mounts:

```shell
rm -rf ./.elasticdata ./.logstashdata ./.filebeatdata ./.kibanadata
```

