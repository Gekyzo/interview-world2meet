package com.excelia.spaceships.infrastructure.in.rest.controllers.delete;

import com.excelia.spaceships.application.exceptions.SpaceshipNotFoundException;
import com.excelia.spaceships.domain.ports.in.DeleteSpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.controllers.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {DeleteSpaceshipControllerImpl.class})
class DeleteSpaceshipControllerImplTest extends ControllerTest {

    @MockBean
    private DeleteSpaceshipPort deleteSpaceship;

    private static final String DELETE_SPACESHIP_URI = "/spaceships/{spaceship-id}";

    @Test
    void given_ValidDeleteSpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsNoContent() throws Exception {

        mockMvc.perform(delete(DELETE_SPACESHIP_URI, aValidSpaceshipId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void given_InvalidDeleteSpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsBadRequest() throws Exception {

        mockMvc.perform(delete(DELETE_SPACESHIP_URI, anInvalidSpaceshipId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void given_InvalidDeleteSpaceshipRequest_when_EndpointIsInvoked_then_ResponseIsNotFound() throws Exception {

        var nonExistentSpaceshipId = aNonExistentSpaceshipId();

        doThrow(new SpaceshipNotFoundException()).when(deleteSpaceship).delete(nonExistentSpaceshipId);

        mockMvc.perform(delete(DELETE_SPACESHIP_URI, nonExistentSpaceshipId))
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

}