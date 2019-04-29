package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.InterviewDTO;
import com.innowise.rm.calendar.app.domain.Interview;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = {CandidateMapper.class})
public interface InterviewMapper {

    @Mappings({})
    InterviewDTO toDTO(Interview interview);

    @Mappings({})
    List<InterviewDTO> toListDTO(List<Interview> interviews);

    @InheritInverseConfiguration
    Interview fromDTO(InterviewDTO interviewDTO);

    @InheritInverseConfiguration
    List<Interview> fromListDTO(List<InterviewDTO> interviewDTOs);
}
