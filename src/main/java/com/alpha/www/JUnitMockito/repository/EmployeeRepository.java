package com.alpha.www.JUnitMockito.repository;

import com.alpha.www.JUnitMockito.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
