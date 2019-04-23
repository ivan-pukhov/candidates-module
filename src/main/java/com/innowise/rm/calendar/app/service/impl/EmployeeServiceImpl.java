package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Employee;
import com.innowise.rm.calendar.app.repository.DepartmentRepository;
import com.innowise.rm.calendar.app.repository.EmployeeRepository;
import com.innowise.rm.calendar.app.repository.InterviewRepository;
import com.innowise.rm.calendar.app.service.api.EmployeeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository,
                               final DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }


    @Override
    @Transactional
    public Employee save(@NonNull final Employee employee) {

        Employee result = employee;
        if (employeeRepository.existsById(employee.getId())) {
            result = employeeRepository.getOne(employee.getId());
        }
        result.setFirstName(employee.getFirstName());
        result.setLastName(employee.getLastName());
        result.setDepartment(employee.getDepartment());
        return employeeRepository.save(result);
    }

    @Override
    @Transactional
    public void reset(@NonNull final  Employee employee) {

        employeeRepository.delete(employee);
    }

    @Override
    @Transactional
    public List<Employee> getEmployeesByDepartmentId(@NonNull final Employee employee) {

        return employeeRepository.findByDepartmentId(employee.getDepartment().getId());
    }

    @Override
    @Transactional
    public Optional<Employee> findById(@NonNull final Long id) {

        Employee employee = employeeRepository.findById(id).orElse(null);
        return Optional.ofNullable(employee);
    }
}
