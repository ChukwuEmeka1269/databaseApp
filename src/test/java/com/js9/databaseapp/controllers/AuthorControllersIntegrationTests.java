package com.js9.databaseapp.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.js9.databaseapp.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc

public class AuthorControllersIntegrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper mapper;

    @Autowired
    public AuthorControllersIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.mapper = new ObjectMapper();
    }


    @Test
    public void testCreateAuthorReturnsHttpResponse201CreatedWhenSuccessful() throws Exception{

        var author = TestDataUtil.createTestAuthorA();
        author.setId(null);

        String authorJsonString = mapper.writeValueAsString(author);

        mockMvc.perform(
                post("/authors")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJsonString)
        )
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                );
    }

    @Test
    public void testCreateAuthorReturnsAValidAuthorObject() throws Exception{

        var author = TestDataUtil.createTestAuthorA();
        author.setId(null);

        String authorJsonString = mapper.writeValueAsString(author);

        mockMvc.perform(
                        post("/authors")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authorJsonString)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.id").isNumber()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.name").value("John Doe")
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.age").value(90)
                );
    }

}
