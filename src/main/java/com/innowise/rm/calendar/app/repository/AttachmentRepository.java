package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
