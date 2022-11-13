package com.alpha.www.JUnitMockito.integration;

import com.alpha.www.JUnitMockito.entity.Employee;
import com.alpha.www.JUnitMockito.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerITests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        employeeRepository.deleteAll();
    }

    // JUnit test for create employee REST api
    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
        // given - pre condition or setup
        Employee employee=Employee.builder()
                .firstName("tony")
                .lastName("stark")
                .email("tony@gmail.com")
                .build();

        // when - action or behavior that we are going to test
        ResultActions response =mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // then - verify the output
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(employee.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(employee.getEmail())));
    }

    // JUnit test for get all employees REST api
    @Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception{
        // given - pre condition or setup
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Employee.builder().firstName("tom").lastName("sharma").email("tom@gmail.com").build());
        listOfEmployees.add(Employee.builder().firstName("tony").lastName("stark").email("tony@gmail.com").build());

        employeeRepository.saveAll(listOfEmployees);

        // when - action or behavior that we are going to test
        ResultActions response=mockMvc.perform(get("/api/employees"));

        // then - verify the output
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        is(listOfEmployees.size())));
    }

    // positive scenario - valid employee id
    // JUnit test for get employee by id REST api
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        // given - pre condition or setup
        Employee employee=Employee.builder()
                .firstName("tony")
                .lastName("stark")
                .email("tony@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or behavior that we are going to test
        ResultActions response=mockMvc.perform(get("/api/employees/{id}", employee.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    // negative scenario - invalid employee id
    // JUnit test for get employee by id REST api
    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
        // given - pre condition or setup
        long employeeId=1L;
        Employee employee=Employee.builder()
                .firstName("tony")
                .lastName("stark")
                .email("tony@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or behavior that we are going to test
        ResultActions response=mockMvc.perform(get("/api/employees/{id}", employeeId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }
}
