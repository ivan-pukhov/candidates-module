package com.innowise.rm.calendar.app.service.api;

import com.innowise.rm.calendar.app.domain.InterviewEmployee;

import java.util.List;

public interface InterviewEmployeeService {

    InterviewEmployee save(InterviewEmployee interviewEmployee);

    void reset(InterviewEmployee interviewEmployee);

    InterviewEmployee update(InterviewEmployee interviewEmployee);

    List<InterviewEmployee> getAllFeedbackByInterviewId(Long interviewId);

    List<InterviewEmployee> getAllByEmployeeId(Long employeeId);

    List<InterviewEmployee> getAllByInterviewId(Long interviewId);
}
