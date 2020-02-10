package kafka.rest.admin.domain.factories

import kafka.rest.admin.exceptions.ClusterConnectionException
import org.springframework.kafka.core.KafkaAdmin
import spock.lang.Specification

class AdminClientFactoryTest extends Specification {
	KafkaAdmin kafkaAdmin = Mock()
	def adminClientFactory = new AdminClientFactory(kafkaAdmin)

	def "buildClient should return domain specific exception"() {
		when:
			adminClientFactory.buildClient()
		then:
			1 * kafkaAdmin.config >> [:]
			thrown(ClusterConnectionException)
	}

	def "buildConsumer should return domain specific exception"() {
		when:
			adminClientFactory.buildConsumer()
		then:
			1 * kafkaAdmin.config >> [:]
			thrown(ClusterConnectionException)
	}
}
