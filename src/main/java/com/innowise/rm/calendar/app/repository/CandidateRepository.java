package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findById(Long id);
}
