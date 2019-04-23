package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterviewRepository  extends JpaRepository<Interview, Long> {

    @Query("select * from interview i where i.candidate_id = :candidateId")
    List<Interview> findByCandidateId(@Param("candidateId") Long candidateId);
}
