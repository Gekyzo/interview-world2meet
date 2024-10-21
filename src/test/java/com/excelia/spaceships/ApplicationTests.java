package com.excelia.spaceships;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Test
    void contextLoads(ApplicationContext context) {
        // Sanity check test that will fail if the application context cannot start
        assertThat(context).isNotNull();
    }

    @Test
    void writeDocumentationSnippets() {

        var modules = ApplicationModules.of(Application.class);
        modules.verify();

        new Documenter(modules)
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml();
    }

}
