package kafka.rest.admin.controllers

import kafka.rest.admin.configuration.IntegrationTest

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TopicControllerIntSpec extends IntegrationTest {
	def "list should return all topics"() {
		when:
			def mvcResult = mockMvc.perform(get('/topics'))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json("[\"testTopicName\"]"))

	}
}
