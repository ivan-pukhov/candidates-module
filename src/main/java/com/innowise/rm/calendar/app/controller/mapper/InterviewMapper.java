package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.CandidateDTO;
import com.innowise.rm.calendar.app.controller.dto.InterviewDTO;
import com.innowise.rm.calendar.app.domain.Interview;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(uses = {CandidateDTO.class})
public interface InterviewMapper {

    @Mappings({})
    InterviewDTO toDTO(Interview interview);

    @InheritInverseConfiguration
    Interview fromDTO(InterviewDTO interviewDTO);
}
