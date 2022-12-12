package com.github.sparsick.workshop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProfileTest {

    @Value("${server.port}")
	private int port;

	@Test
	@EnabledIf(expression = "#{environment['spring.profiles.active'] == 'prod'}", loadContext = true)
	void testProdProfile(){
		assertThat(port).isEqualTo(8090);
	}
}
