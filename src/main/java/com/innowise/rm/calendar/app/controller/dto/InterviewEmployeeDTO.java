package com.innowise.rm.calendar.app.controller.dto;

import lombok.Data;

@Data
public class InterviewEmployeeDTO {

    private String id;
    private EmployeeDTO employee;
    private InterviewDTO interview;
    private String feedback;
}
