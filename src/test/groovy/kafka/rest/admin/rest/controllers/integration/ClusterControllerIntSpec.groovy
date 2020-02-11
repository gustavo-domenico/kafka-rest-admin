package kafka.rest.admin.rest.controllers.integration

import kafka.rest.admin.infrastructure.IntegrationSpec

import static kafka.rest.admin.infrastructure.payloads.Payloads.clusterPayload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ClusterControllerIntSpec extends IntegrationSpec {
	def "get should return cluster details"() {
		when:
			def mvcResult = mockMvc.perform(get("/"))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(clusterPayload()))
	}
}
