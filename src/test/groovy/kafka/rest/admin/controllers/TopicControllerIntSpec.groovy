package kafka.rest.admin.controllers

import kafka.rest.admin.configuration.IntegrationTest

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TopicControllerIntSpec extends IntegrationTest {
	def "should return all topics"() {
		when:
			def mvcResult = mockMvc.perform(get('/topics'))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json("[\"testTopicName\"]"))
	}

	def "return one specific topic information"() {
		when:
			def mvcResult = mockMvc.perform(get('/topics/testTopicName'))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json("[\"Partition(topic = testTopicName, partition = 0, leader = 1, replicas = [1], isr = [1], offlineReplicas = [])\"]"))
	}
}
