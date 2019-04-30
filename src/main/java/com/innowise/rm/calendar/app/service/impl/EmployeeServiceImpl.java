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
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void reset(@NonNull final  Employee employee) {
        Employee result = employeeRepository.getOne(employee.getId());
        result.setDeleted(true);
        employeeRepository.save(result);
    }

    @Override
    @Transactional
    public Employee update(final Employee employee) {
        Employee result = employeeRepository.getOne(employee.getId());
        result.setFirstName(employee.getFirstName());
        result.setLastName(employee.getLastName());
        result.setDepartment(employee.getDepartment());
        updateInterviewEmployees(result, interviewEmployeeRepository.findAllByEmployeeId(employee.getId()));
        return employeeRepository.save(result);
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
        }
        interviewEmployeeRepository.saveAll(interviewEmployees);
    }
}
