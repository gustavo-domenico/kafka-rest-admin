# kafka-rest-admin

## Running

`./gradlew bootRun`

## API documentation

http://localhost:8080/swagger-ui.html

## Examples

### Get one specific message

`curl http://localhost:8080/messages/topic/TransferService.transferStateChange/partition/0/offset/97908`
