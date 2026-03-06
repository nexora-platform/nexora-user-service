package com.nexora.user;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestPostgresContainer.class)
@Disabled("Disabled until infrastructure configuration is ready")
class NexoraUserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
