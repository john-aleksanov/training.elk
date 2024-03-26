# Filebeat, Logstash, Kibana

1. Install ElasticSearch.

2. Install Logstash.

3. Install Kibana.

4. Configure event-service Java application to use log4j and log messages using to the file on file system.

5. Configure Filebeat to read log file and send log messages into the Logstash.

6. Configure Logstash to read logs from the filebeat (beats input plugin) and send logs to Elasticsearch (elasticsearch logstash output
   plugin).

7. Look at result data in the ElasticSearch using Kibana. Task result: application, logstash and filebeat configuration files.

# Logstash Filters

1. Configure event-service Java application to use log4j and log messages using to the file on file system. Log messages should have the
   following pattern:

${MESSAGE_ID} [${UUID} key1="value1" key2="value2" key3="value3" keyN="ValueN"] ${PROCESS_ID} ${COMPONENT_NAME}: ${MESSAGE}
Example:
00-00-00000 [c133ee9a7bfa11e6ae2256b6b6499611 app_name="application-name" app_version="1.0.0-SNAPSHOT" hostname="localhost"]
69427d6c966046c58804d7f4128f7505 DataBase: message here

2. Read all the logs from the file and send them to logstash.

3. Using Logstash filters parse log messages and extract all the fields specified in the message pattern (result document in the
   ElasticSearch should contains all of them in the root of log document).

4. Add ${AUTHOR} field to every log messages using logstash filters.

5. If ${MESSAGE} contains keyword "error" - add tag [failed] to this document.

Note: Use Kibana to make sure logs are properly parsed and contains all required fields.
Task result: application, filebeat and logstash configuration file.