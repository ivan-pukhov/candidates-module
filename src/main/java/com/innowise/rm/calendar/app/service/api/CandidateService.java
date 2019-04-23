package com.innowise.rm.calendar.app.service.api;

import com.innowise.rm.calendar.app.domain.Candidate;

import java.util.Optional;

public interface CandidateService {

    Candidate save(Candidate candidate);

    void reset(Candidate candidate);

    Optional<Candidate> findById(Long id);

}
