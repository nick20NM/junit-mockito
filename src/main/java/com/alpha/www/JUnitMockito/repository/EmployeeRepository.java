package com.alpha.www.JUnitMockito.repository;

import com.alpha.www.JUnitMockito.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // custom query method
    Optional<Employee> findByEmail(String email);

    // JPQL custom query with index params
    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);
}
