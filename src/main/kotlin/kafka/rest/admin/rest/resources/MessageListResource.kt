package kafka.rest.admin.rest.resources

import org.springframework.hateoas.CollectionModel

open class MessageListResource(resources: List<MessageResource>) : CollectionModel<MessageResource>(resources)