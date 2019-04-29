package com.innowise.rm.calendar.app.service.api;

import com.innowise.rm.calendar.app.domain.Attachment;

import java.util.List;
import java.util.Optional;

public interface AttachmentService {

    Attachment save(Attachment attachment);

    void reset(Attachment attachment);

    Attachment update(Attachment attachment);

    Optional<Attachment> getById(Long id);

    List<Attachment> getAttachmentsByCandidateId(Long candidateId);
}
