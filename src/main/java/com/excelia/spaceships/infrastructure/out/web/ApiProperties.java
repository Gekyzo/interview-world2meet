package com.excelia.spaceships.infrastructure.out.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.application")
public class ApiProperties {

    /**
     * Application name.
     */
    private String name;

    /**
     * Application version. Follows semantic versioning standard.
     */
    private String version;

    /**
     * Application summary description.
     */
    private String description;

    @Bean
    public OpenAPI openAPI() {

        OpenAPI customOpenAPI = new OpenAPI();

        Info info = new Info()
            .title(StringUtils.capitalize(name))
            .version(version)
            .description(description);

        Contact contact = new Contact();
        contact.name("Ciro Germán Mora Ruiz");
        contact.email("ciro.mora@gmail.com");
        info.contact(contact);

        License license = new License();
        license.name("Apache 2.0");
        license.url("https://www.apache.org/licenses/LICENSE-2.0.html");
        info.license(license);

        customOpenAPI.info(info);
        return customOpenAPI;
    }

}
