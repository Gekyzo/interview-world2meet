package com.excelia.spaceships;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Test
    void contextLoads(ApplicationContext context) {
        // Sanity check test that will fail if the application context cannot start
        assertThat(context).isNotNull();
    }

}
