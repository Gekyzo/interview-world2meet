package com.excelia.spaceships.infrastructure.in.rest.controllers.put;

import static com.junit.object_mothers.SpaceshipObjectMother.aSpaceship;
import static org.hamcrest.Matchers.matchesPattern;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.excelia.spaceships.domain.ports.in.ModifySpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.controllers.ControllerTest;
import com.excelia.spaceships.infrastructure.in.rest.mappers.ModifySpaceshipRestMapper;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

@Import({ModifySpaceshipRestMapper.class})
@WebMvcTest(controllers = {ModifySpaceshipControllerImpl.class})
class ModifySpaceshipControllerImplTest extends ControllerTest {

    @MockBean
    private ModifySpaceshipPort modifySpaceship;

    private static final String MODIFY_SPACESHIP_URI = "/spaceships/{spaceshipId}";

    @Test
    void given_ValidModifySpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsOk() throws Exception {

        given(modifySpaceship.modify(any())).willReturn(Optional.of(aSpaceship()));

        var request = aValidModifySpaceshipRequest();

        mockMvc.perform(put(MODIFY_SPACESHIP_URI, aValidSpaceshipId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
            .andExpect(status().isOk());
    }

    @Test
    void given_ValidModifySpaceshipRequest_when_EndpointIsInvoked_then_ResponseMatchesExpected() throws Exception {

        given(modifySpaceship.modify(any())).willReturn(Optional.of(aSpaceship()));

        var request = aValidModifySpaceshipRequest();

        mockMvc.perform(put(MODIFY_SPACESHIP_URI, aValidSpaceshipId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
            .andExpect(jsonPath("id", matchesPattern("[0-9a-fA-F-]{36}")))
            .andExpect(jsonPath("name").isString())
            .andExpect(jsonPath("captain_name").isString())
            .andExpect(jsonPath("length").isNumber())
            .andExpect(jsonPath("max_speed").isNumber())
            .andExpect(jsonPath("appears_in").isString());
    }

    @Test
    void given_InvalidSpaceshipIdInPathVariable_when_EndpointIsInvoked_then_ResponseIsBadRequest() throws Exception {

        mockMvc.perform(put(MODIFY_SPACESHIP_URI, anInvalidSpaceshipId()))
            .andExpect(status().isBadRequest());
    }

    @Test
    void given_InvalidModifySpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsBadRequest() throws Exception {

        var request = anInvalidModifySpaceshipRequest();

        mockMvc.perform(put(MODIFY_SPACESHIP_URI, aValidSpaceshipId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
            .andExpect(status().isBadRequest());
    }

    @Test
    void given_ModifyNonExistentSpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsNotFound() throws Exception {

        given(modifySpaceship.modify(any())).willReturn(Optional.empty());

        var request = aValidModifySpaceshipRequest();

        mockMvc.perform(put(MODIFY_SPACESHIP_URI, aNonExistentSpaceshipId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
            .andExpect(status().isNotFound());
    }

    private static UUID aValidSpaceshipId() {
        return UUID.randomUUID();
    }

    private static String anInvalidSpaceshipId() {
        return "::Invalid UUID value::";
    }

    private static UUID aNonExistentSpaceshipId() {
        return aValidSpaceshipId();
    }

    private static String aValidModifySpaceshipRequest() {
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

    private static String anInvalidModifySpaceshipRequest() {
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