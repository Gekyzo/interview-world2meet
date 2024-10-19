package com.excelia.spaceships;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Test
    void contextLoads(ApplicationContext context) {
        // Sanity check test that will fail if the application context cannot start
        assertThat(context).isNotNull();
    }

}
