package com.innowise.rm.calendar.app.service.api;

import com.innowise.rm.calendar.app.domain.Interview;

import java.time.LocalDateTime;
import java.util.List;

public interface InterviewService {

    Interview save(Interview interview);

    void reset(Interview interview);

    List<Interview> getInterviewsByDate(LocalDateTime interviewDate);

    List<Interview> getInterviewsByCandidateId(Long candidateId);

}
