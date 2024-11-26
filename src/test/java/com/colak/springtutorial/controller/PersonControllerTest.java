package com.colak.springtutorial.controller;

import com.colak.dto.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPerson() throws Exception {
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
}