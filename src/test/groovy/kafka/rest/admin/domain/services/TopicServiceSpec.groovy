package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.exceptions.EntityNotFoundException
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.DescribeTopicsResult
import org.apache.kafka.clients.admin.ListTopicsResult
import org.apache.kafka.clients.consumer.KafkaConsumer
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopicDescription
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopicDetail
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicListings
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicPartition
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topics
import static org.apache.kafka.common.KafkaFuture.completedFuture

class TopicServiceSpec extends Specification {
	private static final String INVALID_TOPIC_NAME = "INVALID_TOPIC_NAME"

	ListTopicsResult listTopicsResult = Mock()
	DescribeTopicsResult describeTopicsResult = Mock()

	AdminClient adminClient = Mock()
	AdminClientFactory adminClientFactory = Mock()
	KafkaConsumer<String, String> kafkaConsumer = Mock()

	def topicService = new TopicService(adminClientFactory)

	def "list should return topics"() {
		when:
			def actual = topicService.list()
		then:
			1 * adminClientFactory.buildClient() >> adminClient
			1 * adminClient.listTopics() >> listTopicsResult
			1 * listTopicsResult.listings() >> completedFuture(topicListings())

			actual == topics()
	}

	def "get should return topic details "() {
		when:
			def actual = topicService.get(oneTopic().name)
		then:
			1 * adminClientFactory.buildClient() >> adminClient
			1 * adminClientFactory.buildConsumer() >> kafkaConsumer

			1 * kafkaConsumer.endOffsets(_) >> [(topicPartition()): 10]
			1 * adminClient.describeTopics([oneTopic().name]) >> describeTopicsResult
			1 * describeTopicsResult.values() >> [(oneTopic().name): completedFuture(oneTopicDescription())]

			actual == oneTopicDetail()
	}

	def "get should throw exception if topic does not exist "() {
		when:
			topicService.get(INVALID_TOPIC_NAME)
		then:
			1 * adminClientFactory.buildClient() >> adminClient
			1 * adminClient.describeTopics([INVALID_TOPIC_NAME]) >> { throw new EntityNotFoundException(INVALID_TOPIC_NAME, new Exception()) }

			thrown(EntityNotFoundException.class)
	}
}
