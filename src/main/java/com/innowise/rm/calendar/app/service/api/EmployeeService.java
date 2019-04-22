package com.innowise.rm.calendar.app.service.api;

import com.innowise.rm.calendar.app.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee update(Employee employee);

    void reset(Employee employee);

    List<Employee> getEmployeesByDepartmentId(Long departmentId);

    Optional<Employee> findById(Long id);
}
