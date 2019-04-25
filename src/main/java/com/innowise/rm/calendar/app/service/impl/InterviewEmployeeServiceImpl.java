package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.InterviewEmployee;
import com.innowise.rm.calendar.app.repository.InterviewEmployeeRepository;
import com.innowise.rm.calendar.app.service.api.InterviewEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class InterviewEmployeeServiceImpl implements InterviewEmployeeService {

    private InterviewEmployeeRepository interviewEmployeeRepository;


    @Autowired
    public InterviewEmployeeServiceImpl(final InterviewEmployeeRepository interviewEmployeeRepository) {
        this.interviewEmployeeRepository = interviewEmployeeRepository;
    }

    @Override
    public InterviewEmployee save(final InterviewEmployee interviewEmployee) {
        interviewEmployee.setInterview(interviewEmployee.getInterview());
        interviewEmployee.setEmployee(interviewEmployee.getEmployee());
        interviewEmployee.setFeedback(interviewEmployee.getFeedback());
        interviewEmployee.setCreated(LocalDateTime.now());
        return interviewEmployeeRepository.save(interviewEmployee);
    }

    @Override
    public void reset(final InterviewEmployee interviewEmployee) {
        interviewEmployeeRepository.delete(interviewEmployee);
    }

    @Override
    public InterviewEmployee update(final InterviewEmployee interviewEmployee) {
        interviewEmployee.setInterview(interviewEmployee.getInterview());
        interviewEmployee.setEmployee(interviewEmployee.getEmployee());
        interviewEmployee.setFeedback(interviewEmployee.getFeedback());
        interviewEmployee.setUpdated(LocalDateTime.now());
        return interviewEmployeeRepository.save(interviewEmployee);

    }

    @Override
    public List<InterviewEmployee> getAllFeedbackByInterviewId(final Long interviewId) {
        return interviewEmployeeRepository.findAllFeedbackByInterviewId(interviewId);
    }

    @Override
    public List<InterviewEmployee> getAllByEmployeeId(final Long employeeId) {
        return interviewEmployeeRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    public List<InterviewEmployee> getAllByInterviewId(final Long interviewId) {
        return interviewEmployeeRepository.findAllByInterviewId(interviewId);
    }
}
