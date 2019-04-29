package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.EmployeeDTO;
import com.innowise.rm.calendar.app.domain.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = {DepartmentMapper.class})
public interface EmployeeMapper {

    @Mappings({})
    EmployeeDTO toDTO(Employee employee);

    @Mappings({})
    List<EmployeeDTO> toListDTO(List<Employee> employees);

    @InheritInverseConfiguration
    Employee fromDTO(EmployeeDTO employeeDTO);

    @InheritInverseConfiguration
    List<Employee> fromListDTO(List<EmployeeDTO> employeeDTOs);
}
