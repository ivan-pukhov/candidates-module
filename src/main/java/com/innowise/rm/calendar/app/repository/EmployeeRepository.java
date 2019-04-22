package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
