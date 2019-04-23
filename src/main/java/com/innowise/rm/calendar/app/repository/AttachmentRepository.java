package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query("select * from attachment a where a.candidate_id = :candidateId")
    List<Attachment> findByCandidateId(@Param("candidateId") Long candidateId);
}
