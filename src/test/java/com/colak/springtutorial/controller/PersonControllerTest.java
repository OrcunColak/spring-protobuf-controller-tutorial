package com.colak.springtutorial.controller;

import com.colak.dto.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPersonCompareBytes() throws Exception {
        // Create a sample Person Protobuf object
        Person person = Person.newBuilder()
                .setName("John Doe")
                .setAge(30)
                .build();

        // Convert the Protobuf object to a byte array
        byte[] protobufData = person.toByteArray();

        // Perform the GET request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/person/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/x-protobuf;charset=UTF-8"))  // Check Content-Type header
                .andExpect(MockMvcResultMatchers.content().bytes(protobufData));  // Check if the response body matches the expected Protobuf data
    }

    @Test
    public void testGetPersonParseFrom() throws Exception {
        // Perform the GET request and capture the response
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/person/get"))
                .andReturn();

        // Get the response body as a byte array
        MockHttpServletResponse servletResponse = result.getResponse();
        byte[] responseBytes = servletResponse.getContentAsByteArray();

        // Deserialize the Protobuf response into a Person object
        Person person = Person.parseFrom(responseBytes);

        // Assert that the deserialized data matches the expected values
        assertEquals("John Doe", person.getName());
        assertEquals(30, person.getAge());
    }
}