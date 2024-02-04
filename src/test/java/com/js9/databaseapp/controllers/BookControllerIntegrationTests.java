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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }


    @Test
    public void testThatCreateBookSuccessfullyReturns201Created()throws Exception{
        var bookDto = TestDataUtil.createTestBookDtoA(null);

        String bookJsonString = objectMapper.writeValueAsString(bookDto);


        mockMvc.perform(
                put("/books/123-234-456-2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJsonString)
        ).andExpect(
                status().isCreated()
        );
    }


    @Test
    public void testThatCreateBookSuccessfullyReturnsValidIsbn()throws Exception{
        var bookDto = TestDataUtil.createTestBookDtoA(null);

        String bookJsonString = objectMapper.writeValueAsString(bookDto);


        mockMvc.perform(
                put("/books/123-234-345-1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJsonString)
        ).andExpect(
                jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                jsonPath("$.title").value(bookDto.getTitle())
        );
    }
}
