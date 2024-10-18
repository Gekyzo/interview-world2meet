package com.excelia.spaceships.infrastructure.in.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

}
