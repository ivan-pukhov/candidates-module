package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Attachment;
import com.innowise.rm.calendar.app.repository.AttachmentRepository;
import com.innowise.rm.calendar.app.service.api.AttachmentService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment save(@NonNull final Attachment attachment) {
        Attachment result = attachment;
        if (attachmentRepository.existsById(attachment.getId())) {
            result = attachmentRepository.getOne(attachment.getId());
        }
        result.setFileName(attachment.getFileName());
        result.setPath(attachment.getPath());
        result.setCandidate(attachment.getCandidate());
        return attachmentRepository.save(result);
    }

    @Override
    public void reset(@NonNull final Attachment attachment) {

        attachmentRepository.delete(attachment);
    }

    @Override
    public List<Attachment> getAttachmentsByCandidateId(@NonNull final Attachment attachment) {
        return attachmentRepository.findByCandidateId(attachment.getCandidate().getId());
    }
}
