package kafka.rest.admin.controllers

import kafka.rest.admin.configuration.IntegrationTest
import kafka.rest.admin.domain.models.Topic

import static kafka.rest.admin.infrastructure.routes.Routes.RESOURCE_NAME
import static kafka.rest.admin.infrastructure.routes.Routes.TOPICS
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TopicControllerIntSpec extends IntegrationTest {
	def "should return all topics"() {
		given:
			def expectedTopic = objectMapper.writeValueAsString([new Topic("testTopicName")])
		when:
			def mvcResult = mockMvc.perform(get(TOPICS))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(expectedTopic))
	}

	def "return one specific topic information"() {
		given:
			def expectedTopic = objectMapper.writeValueAsString(new Topic("testTopicName"))
		when:
			def mvcResult = mockMvc.perform(get("${TOPICS}/{${RESOURCE_NAME}}", "testTopicName"))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(expectedTopic))
	}
}
