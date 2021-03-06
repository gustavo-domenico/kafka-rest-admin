package kafka.rest.admin.infrastructure

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.KafkaContainer
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.KafkaDataSetup.loadData

@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(initializers = [Initializer.class])
@DirtiesContext
abstract class IntegrationSpec extends Specification {
	@Autowired
	protected MockMvc mockMvc

	@Autowired
	protected KafkaAdmin kafkaAdmin

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		void initialize(ConfigurableApplicationContext context) {
			def kafkaContainer = new KafkaContainer()
			kafkaContainer.start()

			TestPropertyValues.of("spring.kafka.bootstrap-servers:" + kafkaContainer.getBootstrapServers())
					.applyTo(context)

			loadData(kafkaContainer)
		}
	}
}