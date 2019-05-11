package com.innowise.rm.calendar.app.controller.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String id;
    private String firstName;
    private String lastName;
    private boolean deleted;
    private DepartmentDTO department;
}
