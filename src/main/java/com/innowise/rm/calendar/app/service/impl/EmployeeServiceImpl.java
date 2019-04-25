package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Employee;
import com.innowise.rm.calendar.app.domain.InterviewEmployee;
import com.innowise.rm.calendar.app.repository.DepartmentRepository;
import com.innowise.rm.calendar.app.repository.EmployeeRepository;
import com.innowise.rm.calendar.app.repository.InterviewEmployeeRepository;
import com.innowise.rm.calendar.app.service.api.EmployeeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    private InterviewEmployeeRepository interviewEmployeeRepository;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository,
                               final DepartmentRepository departmentRepository,
                               final InterviewEmployeeRepository interviewEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.interviewEmployeeRepository = interviewEmployeeRepository;
    }


    @Override
    @Transactional
    public Employee save(final Employee employee) {
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setDepartment(employee.getDepartment());
        employee.setCreated(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void reset(@NonNull final  Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Employee update(final Employee employee) {
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setDepartment(employee.getDepartment());
        employee.setUpdated(LocalDateTime.now());
        updateInterviewEmployees(employee, interviewEmployeeRepository.findAllByEmployeeId(employee.getId()));
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public List<Employee> getEmployeesByDepartmentId(final Long departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }

    @Override
    @Transactional
    public Optional<Employee> getById(final Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return Optional.ofNullable(employee);
    }
    private void updateInterviewEmployees(final Employee employee, List<InterviewEmployee> interviewEmployees) {
        for(InterviewEmployee interviewEmployee : interviewEmployees) {
            interviewEmployee.setEmployee(employee);
            interviewEmployee.setUpdated(LocalDateTime.now());
        }
        interviewEmployeeRepository.saveAll(interviewEmployees);
    }
}
