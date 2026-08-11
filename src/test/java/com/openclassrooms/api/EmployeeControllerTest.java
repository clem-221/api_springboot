package com.openclassrooms.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // Import the get method for performing GET requests
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // Import the jsonPath method for asserting JSON responses
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Import the status method for asserting HTTP response status

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; // Import the AutoConfigureMockMvc annotation for configuring MockMvc in tests
import org.springframework.boot.test.context.SpringBootTest; // Import the SpringBootTest annotation for running the test with Spring Boot support
import org.springframework.test.web.servlet.MockMvc; // Import the MockMvc class for performing HTTP requests in tests

@SpringBootTest 
@AutoConfigureMockMvc // This annotation is used to configure MockMvc for testing the web layer of the application
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employees")) // Perform a GET request to the "/employees" endpoint
            .andExpect(status().isOk()) // Assert that the HTTP response status is 200 OK
            .andExpect(jsonPath("$[0].firstName", is("Laurent"))); // Assert that the first employee's first name in the JSON response is "Laurent"
    }

}