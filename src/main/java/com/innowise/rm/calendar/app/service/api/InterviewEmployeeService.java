package com.innowise.rm.calendar.app.service.api;

import com.innowise.rm.calendar.app.domain.InterviewEmployee;

import java.util.List;
import java.util.Optional;

public interface InterviewEmployeeService {

    Optional<InterviewEmployee> getById(Long id);

    InterviewEmployee save(InterviewEmployee interviewEmployee);

    void reset(InterviewEmployee interviewEmployee);

    InterviewEmployee update(InterviewEmployee interviewEmployee);

    List<InterviewEmployee> getAllByEmployeeId(Long employeeId);

    List<InterviewEmployee> getAllByInterviewId(Long interviewId);

    List<InterviewEmployee> getAll();
}
