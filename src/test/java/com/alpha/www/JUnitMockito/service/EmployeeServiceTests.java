package com.alpha.www.JUnitMockito.service;

import com.alpha.www.JUnitMockito.entity.Employee;
import com.alpha.www.JUnitMockito.repository.EmployeeRepository;
import com.alpha.www.JUnitMockito.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;
//    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
//        employeeRepository= Mockito.mock(EmployeeRepository.class);
//        employeeService=new EmployeeServiceImpl(employeeRepository);
        employee=Employee.builder()
                .id(1L)
                .firstName("tony")
                .lastName("stark")
                .email("tony@gmail.com")
                .build();
    }

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){

        // given - pre condition or setup
//        Employee employee=Employee.builder()
//                .id(1L)
//                .firstName("tony")
//                .lastName("stark")
//                .email("tony@gmail.com")
//                .build();

//        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
//                .willReturn(Optional.empty());
//        BDDMockito.given(employeeRepository.save(employee))
//                .willReturn(employee);
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee))
                .willReturn(employee);

        System.out.println(employeeRepository);
//        System.out.println(employeeService);
        System.out.println(employeeServiceImpl);

        // when - action or behavior that we are going to test
//        Employee savedEmployee=employeeService.saveEmployee(employee);
        Employee savedEmployee=employeeServiceImpl.saveEmployee(employee);

        System.out.println(savedEmployee);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }
}
