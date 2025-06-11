package org.springframework.security.kt.docs.servlet.test.testmethodwithmockuser

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.core.MessageService
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration
class WithMockUserSampleTests {

	@Autowired
	lateinit var messageService: MessageService

	// tag::mock-user[]
	@Test
	@WithMockUser
	fun getMessageWithMockUser() {
		val message = messageService.message
		Assertions.assertThat(message).contains("Hello UsernamePasswordAuthenticationToken")
	}
	// end::mock-user[]

	// tag::custom-user[]
	@Test
	@WithMockUser("customUser")
	fun getMessageWithMockUserCustomUsername() {
		val message = messageService.message
		Assertions.assertThat(message).contains("Hello UsernamePasswordAuthenticationToken")
	}
	// end::custom-user[]

	@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
	@Configuration
	open class Config {
		@Bean
		open fun messageService(): MessageService {
			return org.springframework.security.kt.docs.servlet.test.testmethod.HelloMessageService()
		}
	}

}
