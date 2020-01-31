package kafka.rest.admin.rest.controllers

import kafka.rest.admin.infrastructure.IntegrationTest

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupOffsetsPayload
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDetailPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.consumerGroupsPayload
import static kafka.rest.admin.infrastructure.routes.Routes.CONSUMER_GROUPS
import static kafka.rest.admin.infrastructure.routes.Routes.OFFSETS
import static kafka.rest.admin.infrastructure.routes.Routes.RESOURCE_ID
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ConsumerGroupControllerIntSpec extends IntegrationTest {
	def "should return all consumer groups"() {
		when:
			def mvcResult = mockMvc.perform(get(CONSUMER_GROUPS))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(consumerGroupsPayload()))
	}

	def "return one specific consumer group information"() {
		when:
			def mvcResult = mockMvc.perform(get("${CONSUMER_GROUPS}/{${RESOURCE_ID}}", oneConsumerGroup().id))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(oneConsumerGroupDetailPayload()))
	}

	def "return consumer group offsets"() {
		when:
			def mvcResult = mockMvc.perform(get("${CONSUMER_GROUPS}/{${RESOURCE_ID}}/${OFFSETS}", oneConsumerGroup().id))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(consumerGroupOffsetsPayload()))
	}

}
