package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Interview;
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

    @Autowired
    public InterviewServiceImpl(final InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    public Interview save(@NonNull final Interview interview) {
        Interview result = interview;
        if (interviewRepository.existsById(interview.getId())) {
            result = interviewRepository.getOne(interview.getId());
        }
        result.setCandidate(interview.getCandidate());
        result.setInterviewDate(interview.getInterviewDate());
        result.setStatus(interview.getStatus());
        return interviewRepository.save(result);
    }

    @Override
    public void reset(@NonNull final Interview interview) {

        interviewRepository.delete(interview);
    }

    @Override
    public List<Interview> getInterviewsByDate(@NonNull final LocalDateTime interviewDate) {
        return interviewRepository.findAll().stream()
                .filter(interview1 -> interview1.getInterviewDate() == interviewDate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Interview> getInterviewsByCandidateId(@NonNull final Long candidateId) {

        return interviewRepository.findByCandidateId(candidateId);
    }
}
