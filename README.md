# kafka-rest-admin

## Running

`./gradlew bootRun`

## API documentation

http://localhost:8080/swagger-ui.html

## Features

* Cluster
  * Get cluster information
* Topics
  * List topics
  * Get topic information
* Consumer groups
  * List consumer groups
  * Get consumer group information
  * Get consumer group offets
* Messages
  * Get message raw contant for topic/partition/offset
  * Get message for topic/partition/offset
  * Get message from topic/partition/offset
  
### Quick examples

Get all messages from the _TransferService.transferStateChange_ topic, partition 0 and offset 97908:
`curl http://localhost:8080/messages/topic/TransferService.transferStateChange/partition/0/from/97908`
