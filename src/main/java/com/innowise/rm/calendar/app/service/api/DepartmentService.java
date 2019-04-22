package com.innowise.rm.calendar.app.service.api;

import com.innowise.rm.calendar.app.domain.Department;

import java.util.Optional;

public interface DepartmentService {

    Department save(Department department);

    Department update(Department department);

    void reset(Department department);

    Optional<Department> findById(Long id);
}
