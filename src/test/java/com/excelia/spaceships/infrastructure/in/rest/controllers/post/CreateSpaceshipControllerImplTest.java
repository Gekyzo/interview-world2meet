package com.excelia.spaceships.infrastructure.in.rest.controllers.post;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.excelia.spaceships.domain.ports.in.CreateSpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.controllers.ControllerTest;
import com.excelia.spaceships.infrastructure.in.rest.mappers.CreateSpaceshipRestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

@Import({CreateSpaceshipRestMapper.class})
@WebMvcTest(controllers = {CreateSpaceshipControllerImpl.class})
class CreateSpaceshipControllerImplTest extends ControllerTest {

    @MockBean
    private CreateSpaceshipPort createSpaceship;

    private static final String CREATE_SPACESHIP_URI = "/spaceships";

    @Test
    void given_ValidCreateSpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsCreated() throws Exception {

        mockMvc.perform(post(CREATE_SPACESHIP_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(aValidCreateSpaceshipRequest()))
            .andExpect(status().isCreated());
    }

    @Test
    void given_ValidCreateSpaceshipRequest_when_EndpointIsInvoked_then_ResponseMatchesExpected() throws Exception {

        mockMvc
            .perform(post(CREATE_SPACESHIP_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(aValidCreateSpaceshipRequest()))
            .andExpect(header().string("Location", matchesPattern(".*/spaceships/[0-9a-fA-F-]{36}")));
    }

    @Test
    void given_InvalidCreateSpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsBadRequest() throws Exception {

        mockMvc.perform(post(CREATE_SPACESHIP_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(anInvalidCreateSpaceshipRequest()))
            .andExpect(status().isBadRequest());
    }

    private static String aValidCreateSpaceshipRequest() {
        return """
            {
              "name": "Millennium Falcon",
              "captain_name": "Han Solo",
              "length": 34.75,
              "max_speed": 1050,
              "appears_in": "Star Wars"
            }
            """;
    }

    private static String anInvalidCreateSpaceshipRequest() {
        return """
            {
              "name": "Millennium Falcon",
              "captain_name": "Han Solo",
              "length": true,
              "max_speed": 1050,
              "appears_in": "Star Wars"
            }
            """;
    }

}