package com.excelia.spaceships.infrastructure.in.rest.adapter.post;

import com.excelia.spaceships.application.ports.CreateSpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.adapter.ControllerTest;
import com.excelia.spaceships.infrastructure.in.rest.mappers.CreateSpaceshipRestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static com.junit.object_mothers.SpaceshipObjectMother.aSpaceship;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({CreateSpaceshipRestMapper.class})
@WebMvcTest(controllers = {CreateSpaceshipControllerAdapter.class})
class CreateSpaceshipControllerAdapterTest extends ControllerTest {

    @MockBean
    private CreateSpaceshipPort createSpaceship;

    private static final String CREATE_SPACESHIP_URI = "/spaceships";

    @Test
    void given_ValidCreateSpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsCreated() throws Exception {

        given(createSpaceship.create(any())).willReturn(aSpaceship());

        var request = aValidCreateSpaceshipRequest();
        mockMvc.perform(post(CREATE_SPACESHIP_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated());
    }

    @Test
    void given_ValidCreateSpaceshipRequest_when_EndpointIsInvoked_then_ResponseMatchesExpected() throws Exception {

        given(createSpaceship.create(any())).willReturn(aSpaceship());

        var request = aValidCreateSpaceshipRequest();
        mockMvc
                .perform(post(CREATE_SPACESHIP_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(jsonPath("id").isString())
                .andExpect(jsonPath("name").isString())
                .andExpect(jsonPath("captain_name").isString())
                .andExpect(jsonPath("length").isNumber())
                .andExpect(jsonPath("max_speed").isNumber())
                .andExpect(jsonPath("appears_in").isString());
    }

    @Test
    void given_InvalidCreateSpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsBadRequest() throws Exception {

        var request = anInvalidCreateSpaceshipRequest();
        mockMvc.perform(post(CREATE_SPACESHIP_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
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