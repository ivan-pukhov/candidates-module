package com.innowise.rm.calendar.app.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewDTO {

    private String id;
    private CandidateDTO candidateDTO;
    private LocalDateTime interviewDateTime;
    private String status;
}
