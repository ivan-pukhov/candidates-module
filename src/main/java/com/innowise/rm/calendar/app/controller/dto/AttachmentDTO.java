package com.innowise.rm.calendar.app.controller.dto;

import lombok.Data;

@Data
public class AttachmentDTO {

    private String id;
    private CandidateDTO candidate;
    private String fileName;
    private String path;
}
