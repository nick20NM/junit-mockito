package com.alpha.www.JUnitMockito.repository;

import com.alpha.www.JUnitMockito.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryIT {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
        //employeeRepository.deleteAll();
        employee=Employee.builder()
                .firstName("tom")
                .lastName("sharma")
                .email("tom@gmail.com")
                .build();
    }

    // JUnit test for save employee operation
    //@DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        // given - pre condition or setup
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();

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
//        Employee employee1=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();
        Employee employee2=Employee.builder()
                .firstName("jerry")
                .lastName("agrawal")
                .email("jerry@gmail.com")
                .build();
//        employeeRepository.save(employee1);
        employeeRepository.save(employee);
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
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();
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
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();
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
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();
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

    // JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployeeObjectFromDB(){
        // given - pre condition or setup
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or behavior that we are going to test
//        employeeRepository.delete(employee);
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> optionalEmployee=employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(optionalEmployee).isEmpty();
    }

    // JUnit test for JPQL custom query with index params
    @DisplayName("JUnit test for JPQL custom query with index params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){

        // given - pre condition or setup
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();

        employeeRepository.save(employee);
        String firstName="tom";
        String lastName="sharma";

        // when - action or behavior that we are going to test
        Employee savedEmployee=employeeRepository.findByJPQL(firstName,lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for JPQL custom query with named params
    @DisplayName("JUnit test for JPQL custom query with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject(){
        // given - pre condition or setup
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();

        employeeRepository.save(employee);
        String firstName="tom";
        String lastName="sharma";

        // when - action or behavior that we are going to test
        Employee savedEmployee=employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using Native SQL with index params
    @DisplayName("JUnit test for custom query using Native SQL with index params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        // given - pre condition or setup
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();

        employeeRepository.save(employee);
//        String firstName="tom";
//        String lastName="sharma";

        // when - action or behavior that we are going to test
//        Employee savedEmployee=employeeRepository.findByNativeSQL(firstName,lastName);
        Employee savedEmployee=employeeRepository.findByNativeSQL(employee.getFirstName(),employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using native SQL with named params
    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){

        // given - pre condition or setup
//        Employee employee=Employee.builder()
//                .firstName("tom")
//                .lastName("sharma")
//                .email("tom@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or behavior that we are going to test
        Employee savedEmployee=employeeRepository.findByNativeSQLNamedParams(employee.getFirstName(),employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }
}
