package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesPattern;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.FindSpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.controllers.ControllerTest;
import com.excelia.spaceships.infrastructure.in.rest.mappers.SearchSpaceshipRestMapper;
import java.util.ArrayList;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Import({SearchSpaceshipRestMapper.class})
@WebMvcTest(controllers = {SearchSpaceshipsControllerImpl.class})
class SearchSpaceshipsControllerImplTest extends ControllerTest {

    @MockBean
    private FindSpaceshipPort findSpaceship;

    private static final String FIND_SPACESHIP_URI = "/spaceships";

    @Test
    void given_ValidEmptyRequest_when_EndpointIsInvoked_then_ResponseIsOk() throws Exception {

        given(findSpaceship.find(any())).willReturn(Page.empty());

        mockMvc.perform(get(FIND_SPACESHIP_URI))
            .andExpect(status().isOk());
    }

    @Test
    void given_ValidEmptyRequestAndSpaceshipsAreEmpty_when_EndpointIsInvoked_then_ResponseMatchesExpected()
        throws Exception {

        given(findSpaceship.find(any())).willReturn(Page.empty());

        mockMvc.perform(get(FIND_SPACESHIP_URI))
            // Page assertions
            .andExpect(jsonPath("page").exists())
            .andExpect(jsonPath("$.page.size").value(0))
            .andExpect(jsonPath("$.page.totalElements").value(0))
            .andExpect(jsonPath("$.page.totalPages").value(1))
            .andExpect(jsonPath("$.page.number").value(0))
            // Content assertions
            .andExpect(jsonPath("$.content").isEmpty());
    }

    @Test
    void given_ValidEmptyRequestAndSpaceshipsAreNotEmpty_when_EndpointIsInvoked_then_ResponseMatchesExpected()
        throws Exception {

        var content = Instancio.ofList(Spaceship.class).size(5).create();
        given(findSpaceship.find(any())).willReturn(new PageImpl<>(content));

        mockMvc.perform(get(FIND_SPACESHIP_URI))
            // Page assertions
            .andExpect(jsonPath("page").exists())
            .andExpect(jsonPath("$.page.size").value(5))
            .andExpect(jsonPath("$.page.totalElements").value(5))
            .andExpect(jsonPath("$.page.totalPages").value(1))
            .andExpect(jsonPath("$.page.number").value(0))
            // Content assertions
            .andExpect(jsonPath("$.content").isNotEmpty())
            .andExpect(jsonPath("$.content[*].id", everyItem(matchesPattern("[0-9a-fA-F-]{36}"))))
            .andExpect(jsonPath("$.content[*].name", everyItem(isA(String.class))))
            .andExpect(jsonPath("$.content[*].captainName", everyItem(isA(String.class))))
            .andExpect(jsonPath("$.content[*].length", everyItem(isA(Double.class))))
            .andExpect(jsonPath("$.content[*].maxSpeed", everyItem(isA(Integer.class))))
            .andExpect(jsonPath("$.content[*].appearsIn", everyItem(isA(String.class))));
    }

    @Test
    void given_ValidSearchRequest_when_EndpointIsInvoked_then_ResponseIsOk() throws Exception {

        given(findSpaceship.find(any())).willReturn(Page.empty());

        var request = aValidSearchRequest();

        mockMvc.perform(get(FIND_SPACESHIP_URI, request))
            .andExpect(status().isOk());
    }

    @Test
    void given_ValidSearchRequestAndSpaceshipsAreEmpty_when_EndpointIsInvoked_then_ResponseMatchesExpected()
        throws Exception {

        given(findSpaceship.find(any())).willReturn(Page.empty());

        var request = aValidSearchRequest();

        mockMvc.perform(get(FIND_SPACESHIP_URI, request))
            // Page assertions
            .andExpect(jsonPath("page").exists())
            .andExpect(jsonPath("$.page.size").value(0))
            .andExpect(jsonPath("$.page.totalElements").value(0))
            .andExpect(jsonPath("$.page.totalPages").value(1))
            .andExpect(jsonPath("$.page.number").value(0))
            // Content assertions
            .andExpect(jsonPath("$.content").isEmpty());
    }

    @Test
    void given_ValidSearchRequestAndSpaceshipsAreNotEmpty_when_EndpointIsInvoked_then_ResponseMatchesExpected()
        throws Exception {

        List<Spaceship> content = new ArrayList<>();

        content.addAll(Instancio.ofList(Spaceship.class)
            .size(2)
            .generate(field(Spaceship::getName), gen -> gen.oneOf("X-Wing"))
            .create());

        content.addAll(Instancio.ofList(Spaceship.class)
            .size(3)
            .generate(field(Spaceship::getName),
                gen -> gen.oneOf("Millennium Falcon", "USS Enterprise", "Battlestar Galactica"))
            .create());

        given(findSpaceship.find(any())).willReturn(new PageImpl<>(content));

        var request = aValidSearchRequest();

        mockMvc.perform(get(FIND_SPACESHIP_URI, request))
            // Page assertions
            .andExpect(jsonPath("page").exists())
            .andExpect(jsonPath("$.page.size").value(2))
            .andExpect(jsonPath("$.page.totalElements").value(2))
            .andExpect(jsonPath("$.page.totalPages").value(1))
            .andExpect(jsonPath("$.page.number").value(0))
            // Content assertions
            .andExpect(jsonPath("$.content").isNotEmpty())
            .andExpect(jsonPath("$.content[*].id", everyItem(matchesPattern("[0-9a-fA-F-]{36}"))))
            .andExpect(jsonPath("$.content[*].name", everyItem(isA(String.class))))
            .andExpect(jsonPath("$.content[*].captainName", everyItem(isA(String.class))))
            .andExpect(jsonPath("$.content[*].length", everyItem(isA(Double.class))))
            .andExpect(jsonPath("$.content[*].maxSpeed", everyItem(isA(Integer.class))))
            .andExpect(jsonPath("$.content[*].appearsIn", everyItem(isA(String.class))));
    }

    private static String aValidSearchRequest() {
        return "name=wing";
    }

}