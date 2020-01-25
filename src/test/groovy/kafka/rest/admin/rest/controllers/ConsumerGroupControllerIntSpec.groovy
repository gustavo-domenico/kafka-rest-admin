package kafka.rest.admin.rest.controllers

import kafka.rest.admin.infrastructure.IntegrationTest

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupsPayload
import static kafka.rest.admin.infrastructure.routes.Routes.CONSUMER_GROUPS
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

//	def "return one specific topic information"() {
//		when:
//			def mvcResult = mockMvc.perform(get("${Routes.TOPICS}/{${Routes.RESOURCE_NAME}}", oneTopic().name))
//		then:
//			mvcResult.andExpect(status().isOk())
//					.andExpect(content().json(onTopicDetailPayload(port())))
//	}
}
