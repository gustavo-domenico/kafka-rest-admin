package kafka.rest.admin.rest.controllers

import kafka.rest.admin.infrastructure.IntegrationTest

import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.clusterPayload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ClusterControllerIntSpec extends IntegrationTest {
	def "should cluster information"() {
		when:
			def mvcResult = mockMvc.perform(get("/"))
		then:
			mvcResult.andExpect(status().isOk())
					.andExpect(content().json(clusterPayload(port())))
	}
}
