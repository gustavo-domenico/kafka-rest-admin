package kafka.rest.admin.rest.controllers.integration

import kafka.rest.admin.infrastructure.IntegrationSpec

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.payloads.Payloads.consumerGroupDetailPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.consumerGroupOffsetsPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.consumerGroupsPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.invalidConsumerGroupDetailPayload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ConsumerGroupControllerIntSpec extends IntegrationSpec {
	def "list should return all consumer groups"() {
		when:
			def mvcResult = mockMvc.perform(get("/consumer-groups"))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(consumerGroupsPayload()))
	}

	def "get should return consumer group details"() {
		when:
			def mvcResult = mockMvc.perform(get("/consumer-groups/{id}", oneConsumerGroup().id))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(consumerGroupDetailPayload()))
	}

	def "get should return dead state if consumer group does not exist"() {
		when:
			def mvcResult = mockMvc.perform(get("/consumer-groups/{id}", "INVALID_CONSUMER_GROUP_ID"))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(invalidConsumerGroupDetailPayload()))
	}

	def "offsets should return consumer group offsets"() {
		when:
			def mvcResult = mockMvc.perform(get("/consumer-groups/{id}/offsets", oneConsumerGroup().id))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(consumerGroupOffsetsPayload()))
	}
}
