package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Attachment;
import com.innowise.rm.calendar.app.repository.AttachmentRepository;
import com.innowise.rm.calendar.app.service.api.AttachmentService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment save(final Attachment attachment) {
        attachment.setFileName(attachment.getFileName());
        attachment.setPath(attachment.getPath());
        attachment.setCandidate(attachment.getCandidate());
        attachment.setCreated(LocalDateTime.now());
        return attachmentRepository.save(attachment);
    }

    @Override
    public void reset(@NonNull final Attachment attachment) {
        attachmentRepository.delete(attachment);
    }

    @Override
    public Attachment update(final Attachment attachment) {
            attachment.setFileName(attachment.getFileName());
            attachment.setPath(attachment.getPath());
            attachment.setCandidate(attachment.getCandidate());
            attachment.setUpdated(LocalDateTime.now());
            return attachmentRepository.save(attachment);
    }

    @Override
    public List<Attachment> getAttachmentsByCandidateId(final Long candidateId) {
        return attachmentRepository.findAllByCandidateId(candidateId);
    }
}
