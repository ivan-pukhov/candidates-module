package com.innowise.rm.calendar.app.controller.mapper;

import com.innowise.rm.calendar.app.controller.dto.AttachmentDTO;
import com.innowise.rm.calendar.app.domain.Attachment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = {CandidateMapper.class})
public interface AttachmentMapper {

    @Mappings({})
    AttachmentDTO toDTO(Attachment attachment);

    @Mappings({})
    List<AttachmentDTO> toListDTO(List<Attachment> attachments);

    @InheritInverseConfiguration
    Attachment fromDTO(AttachmentDTO attachmentDTO);

    @InheritInverseConfiguration
    List<Attachment> fromListDTO(List<AttachmentDTO> attachmentDTOs);

}
