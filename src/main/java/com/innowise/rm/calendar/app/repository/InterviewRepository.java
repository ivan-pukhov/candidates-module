package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository  extends JpaRepository<Interview, Long> {

    List<Interview> findAllByCandidateId(Long candidateId);
}
