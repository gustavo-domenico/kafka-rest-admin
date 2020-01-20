package kafka.rest.admin.controllers

import kafka.rest.admin.infrastructure.IntegrationTest

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.onTopicDetailPayload
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicsPayload
import static kafka.rest.admin.infrastructure.routes.Routes.RESOURCE_NAME
import static kafka.rest.admin.infrastructure.routes.Routes.TOPICS
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TopicControllerIntSpec extends IntegrationTest {
	def "should return all topics"() {
		when:
			def mvcResult = mockMvc.perform(get(TOPICS))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(topicsPayload()))
	}

	def "return one specific topic information"() {
		when:
			def mvcResult = mockMvc.perform(get("${TOPICS}/{${RESOURCE_NAME}}", oneTopic().name))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(onTopicDetailPayload(port())))
	}
}
