package com.innowise.rm.calendar.app.controller.dto;

import lombok.Data;

@Data
public class AttachmentDTO {

    private String id;
    private CandidateDTO candidateDTO;
    private String fileName;
    private String path;
}
