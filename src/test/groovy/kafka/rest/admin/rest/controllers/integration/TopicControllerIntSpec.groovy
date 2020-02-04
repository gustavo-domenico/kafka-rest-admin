package kafka.rest.admin.rest.controllers.integration

import kafka.rest.admin.infrastructure.IntegrationSpec

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.onTopicDetailPayload
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.payloads.Payloads.topicsPayload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TopicControllerIntSpec extends IntegrationSpec {
	def "should return all topics"() {
		when:
			def mvcResult = mockMvc.perform(get("/topics"))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(topicsPayload()))
	}

	def "return one specific topic information"() {
		when:
			def mvcResult = mockMvc.perform(get("/topics/{name}", oneTopic().name))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(onTopicDetailPayload(port())))
	}
}
