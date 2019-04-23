package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findById(Long id);
}
