package com.alpha.www.JUnitMockito.repository;

import com.alpha.www.JUnitMockito.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // custom query method
    Optional<Employee> findByEmail(String email);
}
