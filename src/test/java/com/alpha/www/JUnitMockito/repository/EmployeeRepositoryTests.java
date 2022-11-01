package com.alpha.www.JUnitMockito.repository;

import com.alpha.www.JUnitMockito.entity.Employee;
//import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for save employee operation
    //@DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        // given - pre condition or setup
        Employee employee=Employee.builder()
                .firstName("tom")
                .lastName("sharma")
                .email("tom@gmail.com")
                .build();

        // when - action or behavior that we are going to test
        Employee savedEmployee=employeeRepository.save(employee);

        // then - verify the output
//        Assertions.assertThat(savedEmployee).isNotNull();
//        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // JUnit test for get all employess operation
    @DisplayName("JUnit test for get all employess operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenReturnEmployeesList(){

        // given - pre condition or setup
        Employee employee1=Employee.builder()
                .firstName("tom")
                .lastName("sharma")
                .email("tom@gmail.com")
                .build();
        Employee employee2=Employee.builder()
                .firstName("jerry")
                .lastName("agrawal")
                .email("jerry@gmail.com")
                .build();
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when - action or behavior that we are going to test
        List<Employee> employeesList =employeeRepository.findAll();

        // then - verify the output
        assertThat(employeesList).isNotNull();
        assertThat(employeesList.size()).isEqualTo(2);
    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObjectFromDB(){
        // given - pre condition or setup
        Employee employee=Employee.builder()
                .firstName("tom")
                .lastName("sharma")
                .email("tom@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or behavior that we are going to test
        Employee employeeDB=employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for get employee by email operation
    @Test
    @DisplayName("JUnit test for get employee by email operation")
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){

        // given - pre condition or setup
        Employee employee=Employee.builder()
                .firstName("tom")
                .lastName("sharma")
                .email("tom@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or behavior that we are going to test
        Employee employeeDB=employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){

        // given - pre condition or setup
        Employee employee=Employee.builder()
                .firstName("tom")
                .lastName("sharma")
                .email("tom@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or behavior that we are going to test
        Employee savedEmployee=employeeRepository.findById(employee.getId()).get();
        savedEmployee.setFirstName("tommy");
        savedEmployee.setEmail("tommy@gmail.com");
        Employee updatedEmployee=employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getFirstName()).isEqualTo("tommy");
        assertThat(updatedEmployee.getEmail()).isEqualTo("tommy@gmail.com");
    }

}