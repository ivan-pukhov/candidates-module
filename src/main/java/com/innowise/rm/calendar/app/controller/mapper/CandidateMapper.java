package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.CandidateDTO;
import com.innowise.rm.calendar.app.domain.Candidate;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper
public interface CandidateMapper {

    @Mappings({})
    CandidateDTO toDTO(Candidate candidate);

    @InheritInverseConfiguration
    Candidate fromDTO(CandidateDTO candidateDTO);
}
