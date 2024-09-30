package com.example.employee.controllers;

import com.example.employee.models.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@Tag("integration")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmployeeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("user");
        employee.setId(1L);
        employee.setAddress("ok");
        employee.setSalary(1000L);
        employee.setAge(5);

        String json = new ObjectMapper().writeValueAsString(employee);
        MockHttpServletRequestBuilder requestBuilders = post("/employees")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions response = mockMvc.perform(requestBuilders);

        response.andExpect(status().isCreated())
                .andExpect(content().json(json))
                .andExpect(jsonPath("$.id").value(1));


    }

    @Test
    void createEmployee_success() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setAge(30);
        employee.setSalary(50000);
        employee.setAddress("123 Main Street");

        // Convert the Employee object to JSON
        String json = new ObjectMapper().writeValueAsString(employee);

        // Build the request
        MockHttpServletRequestBuilder requestBuilders = post("/employees")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        // Perform the request using MockMvc
        ResultActions response = mockMvc.perform(requestBuilders);

        // Verify the response
        response.andExpect(status().isCreated())
                .andExpect(content().json(json))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.salary").value(50000))
                .andExpect(jsonPath("$.address").value("123 Main Street"));
    }

    @Test
    void getAllEmployees() {
    }
}