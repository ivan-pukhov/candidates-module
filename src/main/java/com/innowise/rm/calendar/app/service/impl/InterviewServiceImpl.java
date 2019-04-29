package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Interview;
import com.innowise.rm.calendar.app.domain.InterviewEmployee;
import com.innowise.rm.calendar.app.repository.InterviewEmployeeRepository;
import com.innowise.rm.calendar.app.repository.InterviewRepository;
import com.innowise.rm.calendar.app.service.api.InterviewService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    @Transactional
    public Interview save(final Interview interview) {
        interview.setCandidate(interview.getCandidate());
        interview.setInterviewDate(interview.getInterviewDate());
        interview.setStatus(interview.getStatus());
        return interviewRepository.save(interview);
    }

    @Override
    @Transactional
    public void reset(@NonNull final Interview interview) {

        interviewRepository.delete(interview);
    }

    @Override
    @Transactional
    public Interview update(final Interview interview) {
        interview.setCandidate(interview.getCandidate());
        interview.setInterviewDate(interview.getInterviewDate());
        interview.setStatus(interview.getStatus());
        updateInterviewEmployees(interview, interviewEmployeeRepository.findAllByInterviewId(interview.getId()));
        return interviewRepository.save(interview);
    }

    @Override
    @Transactional
    public Optional<Interview> getById(final Long id) {
        Interview interview = interviewRepository.findById(id).orElse(null);
        return Optional.ofNullable(interview);
    }

    @Override
    @Transactional
    public List<Interview> getInterviewsByDate(final LocalDateTime interviewDate) {
        return interviewRepository.findAll().stream()
                .filter(interview1 -> interview1.getInterviewDate() == interviewDate)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Interview> getInterviewsByCandidateId(final Long candidateId) {

        return interviewRepository.findAllByCandidateId(candidateId);
    }
    private void updateInterviewEmployees(final Interview interview, List<InterviewEmployee> interviewEmployees) {

        for(InterviewEmployee interviewEmployee : interviewEmployees) {
            interviewEmployee.setInterview(interview);
        }
        interviewEmployeeRepository.saveAll(interviewEmployees);
    }
}
