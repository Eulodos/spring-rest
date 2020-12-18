package com.aw.train.guiderest.repository;

import com.aw.train.guiderest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
