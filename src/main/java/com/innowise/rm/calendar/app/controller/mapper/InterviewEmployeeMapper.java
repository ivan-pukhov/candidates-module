package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.EmployeeDTO;
import com.innowise.rm.calendar.app.controller.dto.InterviewDTO;
import com.innowise.rm.calendar.app.controller.dto.InterviewEmployeeDTO;
import com.innowise.rm.calendar.app.domain.InterviewEmployee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = {InterviewDTO.class, EmployeeDTO.class})
public interface InterviewEmployeeMapper {

    @Mappings({})
    InterviewEmployeeDTO toDTO(InterviewEmployee interviewEmployee);

    @Mappings({})
    List<InterviewEmployeeDTO> toListDTO(List<InterviewEmployee> interviewEmployees);

    @InheritInverseConfiguration
    InterviewEmployee fromDTO(InterviewEmployeeDTO interviewEmployeeDTO);

    @InheritInverseConfiguration
    List<InterviewEmployee> fromListDTO(List<InterviewEmployeeDTO> interviewEmployeeDTOs);
}
