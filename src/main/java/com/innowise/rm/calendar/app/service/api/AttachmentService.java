package com.innowise.rm.calendar.app.service.api;

import com.innowise.rm.calendar.app.domain.Attachment;

import java.util.List;

public interface AttachmentService {

    Attachment save(Attachment attachment);

    void reset(Attachment attachment);

    Attachment update(Attachment attachment);

    List<Attachment> getAttachmentsByCandidateId(Long candidateId);
}
