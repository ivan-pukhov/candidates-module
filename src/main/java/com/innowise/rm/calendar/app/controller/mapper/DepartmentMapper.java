package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.DepartmentDTO;
import com.innowise.rm.calendar.app.domain.Department;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Mappings({})
    DepartmentDTO toDTO(Department department);

    @Mappings({})
    List<DepartmentDTO> toListDTO(List<Department> departments);

    @InheritInverseConfiguration
    Department fromDTO(DepartmentDTO departmentDTO);

    @InheritInverseConfiguration
    List<Department> fromListDTO(List<DepartmentDTO> departmentDTOs);
}
