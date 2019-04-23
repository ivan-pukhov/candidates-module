package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select * from employee e where e.department_id = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);
}
