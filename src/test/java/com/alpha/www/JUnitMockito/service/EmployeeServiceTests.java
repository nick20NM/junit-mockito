package com.alpha.www.JUnitMockito.service;

import com.alpha.www.JUnitMockito.entity.Employee;
import com.alpha.www.JUnitMockito.repository.EmployeeRepository;
import com.alpha.www.JUnitMockito.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;

public class EmployeeServiceTests {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    public void setup(){
        employeeRepository= Mockito.mock(EmployeeRepository.class);
        employeeService=new EmployeeServiceImpl(employeeRepository);
    }

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){

        // given - pre condition or setup
        Employee employee=Employee.builder()
                .id(1L)
                .firstName("tony")
                .lastName("stark")
                .email("tony@gmail.com")
                .build();

        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee))
                .willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when - action or behavior that we are going to test
        Employee savedEmployee=employeeService.saveEmployee(employee);

        System.out.println(savedEmployee);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }
}