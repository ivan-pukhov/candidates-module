package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Attachment;
import com.innowise.rm.calendar.app.repository.AttachmentRepository;
import com.innowise.rm.calendar.app.service.api.AttachmentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(final AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    @Transactional
    public Attachment save(final Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    @Transactional
    public void reset(@NonNull final Attachment attachment) {
        attachmentRepository.delete(attachment);
    }

    @Override
    @Transactional
    public Attachment update(final Attachment attachment) {
        Attachment result = attachmentRepository.getOne(attachment.getId());
        result.setFileName(attachment.getFileName());
        result.setPath(attachment.getPath());
        result.setCandidate(attachment.getCandidate());
        return attachmentRepository.save(result);
    }

    @Override
    @Transactional
    public Optional<Attachment> getById(final Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElse(null);
        return Optional.ofNullable(attachment);
    }

    @Override
    @Transactional
    public List<Attachment> getAttachmentsByCandidateId(final Long candidateId) {
        return attachmentRepository.findAllByCandidateId(candidateId);
    }
}
