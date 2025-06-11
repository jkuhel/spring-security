package org.springframework.security.docs.servlet.test.testmethodwithmockuser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.core.MessageService;
import org.springframework.security.docs.servlet.test.testmethod.HelloMessageService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration
class WithMockUserTests {

	@Autowired
	MessageService messageService;

	// tag::mock-user[]
	@Test
	@WithMockUser
	void getMessageWithMockUser() {
		String message = messageService.getMessage();
		assertThat(message).contains("Hello UsernamePasswordAuthenticationToken");
	}
	// end::mock-user[]

	// tag::custom-user[]
	@Test
	@WithMockUser("customUser")
	void getMessageWithMockUserCustomUsername() {
		String message = messageService.getMessage();
		assertThat(message).contains("Hello UsernamePasswordAuthenticationToken");
	}
	// end::custom-user[]

	@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
	@Configuration
	static class Config {

		@Bean
		MessageService messageService() {
			return new HelloMessageService();
		}
	}
}
