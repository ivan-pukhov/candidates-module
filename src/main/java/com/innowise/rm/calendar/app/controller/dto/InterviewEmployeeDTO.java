package com.innowise.rm.calendar.app.controller.dto;

import lombok.Data;

@Data
public class InterviewEmployeeDTO {

    private String id;
    private EmployeeDTO employeeDTO;
    private InterviewDTO interviewDTO;
    private String feedback;
}
