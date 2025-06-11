package org.springframework.security.docs.servlet.test.testmethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.core.MessageService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
class HelloServiceTests {

	@Autowired
	MessageService messageService;

	@BeforeEach
	void setup() {
		UsernamePasswordAuthenticationToken user = UsernamePasswordAuthenticationToken.authenticated("user", "password", AuthorityUtils.createAuthorityList("ROLE_USER"));
		SecurityContextHolder.getContext().setAuthentication(user);
	}

	@Test
	void helloServiceTest() {
		assertThat(messageService.getMessage()).contains("Hello UsernamePasswordAuthenticationToken");
	}

	@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
	@Configuration
	static class Config {

		@Bean
		MessageService messageService() {
			return new HelloMessageService();
		}
	}
}
