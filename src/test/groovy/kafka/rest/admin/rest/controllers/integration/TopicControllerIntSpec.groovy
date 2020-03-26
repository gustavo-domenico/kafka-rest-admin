package kafka.rest.admin.rest.controllers.integration

import kafka.rest.admin.infrastructure.IntegrationSpec

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.payloads.Payloads.newTopicDetailPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.topicDetailPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.topicRequestPayload
import static kafka.rest.admin.infrastructure.payloads.Payloads.topicsPayload
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TopicControllerIntSpec extends IntegrationSpec {
	def "list should return topics"() {
		when:
			def mvcResult = mockMvc.perform(get("/topics"))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(topicsPayload()))
	}

	def "get should return topic details"() {
		when:
			def mvcResult = mockMvc.perform(get("/topics/{name}", oneTopic().name))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(topicDetailPayload()))
	}

	def "get should return 404 if topic does not exist"() {
		when:
			def mvcResult = mockMvc.perform(get("/topics/{name}", "INVALID_TOPIC_NAME" ))
		then:
			mvcResult.andExpect(status().isNotFound())
	}

	def "add should create a new topic"() {
		when:
			def mvcResult = mockMvc.perform(
					post("/topics")
							.contentType(APPLICATION_JSON)
							.content(topicRequestPayload()))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(newTopicDetailPayload()))
	}

	def "delete should delete a topic"() {
		given:
			mockMvc.perform(
				post("/topics")
						.contentType(APPLICATION_JSON)
						.content(topicRequestPayload()))
		when:
		def mvcResult = mockMvc.perform(
				post("/topics")
						.contentType(APPLICATION_JSON)
						.content(topicRequestPayload()))
		then:
			mvcResult.andExpect(status().isNoContent())
	}
}
