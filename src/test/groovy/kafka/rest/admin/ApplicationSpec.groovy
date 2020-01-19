package kafka.rest.admin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class ApplicationSpec extends Specification {
	@Autowired
	private MockMvc mockMvc

	def "application has started successfully"() {
		when:
			def mvcResult = mockMvc.perform(get('/actuator/health'))
		then:
			mvcResult.andExpect(status().isOk())
	}
}
