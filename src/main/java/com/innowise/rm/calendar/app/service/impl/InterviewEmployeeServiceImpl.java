package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.InterviewEmployee;
import com.innowise.rm.calendar.app.repository.InterviewEmployeeRepository;
import com.innowise.rm.calendar.app.service.api.InterviewEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InterviewEmployeeServiceImpl implements InterviewEmployeeService {

    private InterviewEmployeeRepository interviewEmployeeRepository;


    @Autowired
    public InterviewEmployeeServiceImpl(final InterviewEmployeeRepository interviewEmployeeRepository) {
        this.interviewEmployeeRepository = interviewEmployeeRepository;
    }

    @Override
    @Transactional
    public InterviewEmployee save(final InterviewEmployee interviewEmployee) {
        return interviewEmployeeRepository.save(interviewEmployee);
    }

    @Override
    @Transactional
    public void reset(final InterviewEmployee interviewEmployee) {
        interviewEmployeeRepository.delete(interviewEmployee);
    }

    @Override
    @Transactional
    public InterviewEmployee update(final InterviewEmployee interviewEmployee) {
        InterviewEmployee result = interviewEmployeeRepository.getOne(interviewEmployee.getId());
        result.setInterview(interviewEmployee.getInterview());
        result.setEmployee(interviewEmployee.getEmployee());
        result.setFeedback(interviewEmployee.getFeedback());
        return interviewEmployeeRepository.save(result);

    }

    @Override
    @Transactional
    public List<InterviewEmployee> getAllByEmployeeId(final Long employeeId) {
        return interviewEmployeeRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    @Transactional
    public List<InterviewEmployee> getAllByInterviewId(final Long interviewId) {
        return interviewEmployeeRepository.findAllByInterviewId(interviewId);
    }
}
