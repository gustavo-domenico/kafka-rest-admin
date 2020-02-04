package kafka.rest.admin.rest.controllers.integration

import kafka.rest.admin.infrastructure.IntegrationSpec

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDetailPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.consumerGroupOffsetsPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.consumerGroupsPayload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ConsumerGroupControllerIntSpec extends IntegrationSpec {
	def "should return all consumer groups"() {
		when:
			def mvcResult = mockMvc.perform(get("/consumer-groups"))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(consumerGroupsPayload()))
	}

	def "return one specific consumer group information"() {
		when:
			def mvcResult = mockMvc.perform(get("/consumer-groups/{id}", oneConsumerGroup().id))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(oneConsumerGroupDetailPayload()))
	}

	def "return consumer group offsets"() {
		when:
			def mvcResult = mockMvc.perform(get("/consumer-groups/{id}/offsets", oneConsumerGroup().id))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(consumerGroupOffsetsPayload()))
	}

}
