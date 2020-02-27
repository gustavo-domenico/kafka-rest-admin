# kafka-rest-admin

## Running

`export ENV_KAFKA_BOOTSTRAP_SERVERS=[kafka-bootstrap-servers]`

`./gradlew bootRun`

## API documentation

http://localhost:8080/swagger-ui.html

## Features

* Cluster
  * Get cluster information
* Topics
  * List topics
  * Get topic information
  * Create topics
* Consumer groups
  * List consumer groups
  * Get consumer group information
  * Get consumer group offets
* Messages
  * Get message raw contant for topic/partition/offset
  * Get message for topic/partition/offset
  * Get messages from topic/partition/offset
  * Get last messages from a topic/partition
  * Send a message to the topic
  * Send a messsage to the topic/partition
  
### Quick examples

* Get all messages from the _TransferService.transferStateChange_ topic, partition 0 and offset 97908:

`curl http://localhost:8080/messages/topic/TransferService.transferStateChange/partition/0/from/97908`

* Get all offsets for the document reporting consumer group:

`curl http://localhost:8080/consumer-groups/document-reporting-service/offsets`

_Note: try out in Postman :)_
