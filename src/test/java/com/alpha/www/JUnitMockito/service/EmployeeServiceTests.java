package com.alpha.www.JUnitMockito.service;

import com.alpha.www.JUnitMockito.entity.Employee;
import com.alpha.www.JUnitMockito.exception.ResourceNotFoundException;
import com.alpha.www.JUnitMockito.repository.EmployeeRepository;
import com.alpha.www.JUnitMockito.service.impl.EmployeeServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
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
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for saveEmployee method which throws exception
    @DisplayName("JUnit test for saveEmployee method which throws exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){

        // given - pre condition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        System.out.println(employeeRepository);
        System.out.println(employeeServiceImpl);

        // when - action or behavior that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            employeeServiceImpl.saveEmployee(employee);
        });

        // then - verify the output
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - pre condition or setup
        Employee employee1=Employee.builder()
                .id(2L)
                .firstName("shin")
                .lastName("chan")
                .email("shinchan.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // when - action or behavior that we are going to test
        List<Employee> employeeList=employeeServiceImpl.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    // JUnit test for getAllEmployess method (negative scenario)
    @DisplayName("JUnit test for getAllEmployess method (negative scenario)")
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){
        // given - pre condition or setup
        Employee employee1=Employee.builder()
                .id(2L)
                .firstName("shin")
                .lastName("chan")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when - action or behavior that we are going to test
        List<Employee> employeeList=employeeServiceImpl.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(0);
    }

    // JUnit test for getEmployeeById method
    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        // given - pre condition or setup
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // when - action or behavior that we are going to test
        Employee savedEmployee=employeeServiceImpl.getEmployeeById(employee.getId()).get();

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for updateEmployee method
    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - pre condition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setFirstName("shin");
        employee.setEmail("shin@gmail.com");

        // when - action or behavior that we are going to test
        Employee updatedEmployee=employeeServiceImpl.updateEmployee(employee);

        // then - verify the output
        assertThat(updatedEmployee.getFirstName()).isEqualTo("shin");
        assertThat(updatedEmployee.getEmail()).isEqualTo("shin@gmail.com");
    }

    // JUnit test for deleteEmployee method
    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing(){
        // given - pre condition or setup
        long employeeId = 1L;

        willDoNothing().given(employeeRepository).deleteById(employeeId);

        // when - action or behavior that we are going to test
        employeeServiceImpl.deleteEmployee(employeeId);

        // then - verify the output
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}
