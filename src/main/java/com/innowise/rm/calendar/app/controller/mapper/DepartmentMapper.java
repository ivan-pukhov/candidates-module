package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.DepartmentDTO;
import com.innowise.rm.calendar.app.domain.Department;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper
public interface DepartmentMapper {

    @Mappings({})
    DepartmentDTO toDTO(Department department);

    @InheritInverseConfiguration
    Department fromDTO(DepartmentDTO departmentDTO);
}
