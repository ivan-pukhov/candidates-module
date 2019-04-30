package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.CandidateDTO;
import com.innowise.rm.calendar.app.domain.Candidate;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface CandidateMapper {

    @Mappings({})
    CandidateDTO toDTO(Candidate candidate);

    @Mappings({})
    List<CandidateDTO> toListDTO(List<Candidate> candidates);

    @InheritInverseConfiguration
    Candidate fromDTO(CandidateDTO candidateDTO);

    @InheritInverseConfiguration
    List<Candidate> fromListDTO(List<CandidateDTO> candidateDTOs);
}
