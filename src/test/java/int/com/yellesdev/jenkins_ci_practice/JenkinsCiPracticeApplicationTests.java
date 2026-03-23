package com.yellesdev.jenkins_ci_practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(properties = "spring.flyway.enabled=false")
class JenkinsCiPracticeApplicationTests {

	@Test
	void contextLoads() {
	}

}
