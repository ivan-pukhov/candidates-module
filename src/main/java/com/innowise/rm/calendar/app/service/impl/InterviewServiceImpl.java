package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Interview;
import com.innowise.rm.calendar.app.domain.InterviewEmployee;
import com.innowise.rm.calendar.app.repository.InterviewEmployeeRepository;
import com.innowise.rm.calendar.app.repository.InterviewRepository;
import com.innowise.rm.calendar.app.service.api.InterviewService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {

    private InterviewRepository interviewRepository;

    private InterviewEmployeeRepository interviewEmployeeRepository;

    @Autowired
    public InterviewServiceImpl(final InterviewRepository interviewRepository,
                                final InterviewEmployeeRepository interviewEmployeeRepository) {
        this.interviewRepository = interviewRepository;
        this.interviewEmployeeRepository = interviewEmployeeRepository;
    }

    @Override
    public Interview save(final Interview interview) {
        interview.setCandidate(interview.getCandidate());
        interview.setInterviewDate(interview.getInterviewDate());
        interview.setStatus(interview.getStatus());
        interview.setCreated(LocalDateTime.now());
        return interviewRepository.save(interview);
    }

    @Override
    public void reset(@NonNull final Interview interview) {

        interviewRepository.delete(interview);
    }

    @Override
    public Interview update(final Interview interview) {
        interview.setCandidate(interview.getCandidate());
        interview.setInterviewDate(interview.getInterviewDate());
        interview.setStatus(interview.getStatus());
        interview.setUpdated(LocalDateTime.now());
        updateInterviewEmployees(interview, interviewEmployeeRepository.findAllByInterviewId(interview.getId()));
        return interviewRepository.save(interview);
    }

    @Override
    public List<Interview> getInterviewsByDate(final LocalDateTime interviewDate) {
        return interviewRepository.findAll().stream()
                .filter(interview1 -> interview1.getInterviewDate() == interviewDate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Interview> getInterviewsByCandidateId(final Long candidateId) {

        return interviewRepository.findAllByCandidateId(candidateId);
    }
    private void updateInterviewEmployees(final Interview interview, List<InterviewEmployee> interviewEmployees) {

        for(InterviewEmployee interviewEmployee : interviewEmployees) {
            interviewEmployee.setInterview(interview);
            interviewEmployee.setUpdated(LocalDateTime.now());
        }
        interviewEmployeeRepository.saveAll(interviewEmployees);
    }
}
